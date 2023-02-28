package com.trdz.dictionary.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trdz.dictionary.model.data.DataLine
import com.trdz.dictionary.model.data.DataWord
import com.trdz.dictionary.model.Repository
import com.trdz.dictionary.model.data.RequestResults
import com.trdz.dictionary.model.data.VisualState
import com.trdz.dictionary.utility.IN_BASIS
import com.trdz.dictionary.utility.IN_SERVER
import com.trdz.dictionary.utility.IN_STORAGE
import com.trdz.dictionary.utility.TYPE_TITLE
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class WordsViewModel(
	private val repository: Repository,
	private val dataLive: SingleLiveData<StatusProcess>,
): ViewModel(){

	private var currentData: MutableList<DataWord> = listOf<DataWord>().toMutableList()

	fun getData(): LiveData<StatusProcess> = dataLive

	private val coroutineScope = CoroutineScope(
		Dispatchers.IO
				+ SupervisorJob()
				+ CoroutineExceptionHandler { _, throwable ->
			handleError(throwable)
		})

	private fun handleError(throwable: Throwable) {
		Log.w("@@@", "Prs - Coroutine dead $throwable")
	}

	private var jobs: Job? = null

	private val querySearch = MutableStateFlow("")

	init {
		controlledInit()
	}

	fun controlledSet(list: MutableList<DataWord>) {
		currentData = list
	}

	fun controlledInit() {
		CoroutineScope(Dispatchers.Main).launch {
			querySearch.debounce(750)
				.filter { query -> return@filter query.isNotEmpty() }
				.distinctUntilChanged()
				.collect { result -> startSearch(result) }
		}
	}

	override fun onCleared() {
		jobs?.cancel()
		super.onCleared()
	}

	fun setSearch(search: String) {
		querySearch.value = search
		startSearch(search)
	}

	fun getDataTest(): LiveData<ScreenState> = liveData
	private val dataLivetest = MutableLiveData<ScreenState>()
	private val liveData: LiveData<ScreenState> = dataLivetest
	fun searchGitHub(searchQuery: String) {
		dataLivetest.value = ScreenState.Loading
		coroutineScope.launch {
			val searchResponse = "1"
			val searchResults = searchResponse
			val totalCount = searchResponse
			if (searchResults != null && totalCount != null) {
				dataLivetest.value = ScreenState.Working(searchResponse)
			} else {
				dataLivetest.value =
					ScreenState.Error(Throwable("Search results or total count are null"))
			}
		}
	}
	sealed class ScreenState {
		object Loading : ScreenState()
		data class Working(val searchResponse: String) : ScreenState()
		data class Error(val error: Throwable) : ScreenState()
	}

	private fun startSearch(target: String) {
		with(dataLive) {
			postValue(StatusProcess.Loading)
			jobs = coroutineScope.launch {
				when (val response = repository.initWordList(target)) {
					is RequestResults.SuccessWords -> {
						Log.d("@@@", "Prs - External load complete")
						currentData = emptyData(repository.analyze(response.data.dataWord!!)).toMutableList()
						postValue(StatusProcess.Success(ModelResult(currentData)))

					}
					is RequestResults.Error -> {
						Log.e("@@@", "Prs - Loading failed ${response.error}")
						postValue(StatusProcess.Error(-2, response.error))
					}
					else -> {
						Log.w("@@@", "Prs - Unwanted Package - $response")}
				}
			}
		}
	}

	private val basisData = listOf<DataWord>(
		DataWord(name = "Не найдено", subName = "Этого слова нет в ScyEng базе", id = 0, iconUrl = "",
			visual = VisualState(type = TYPE_TITLE, group = 1)),
	)
	private fun emptyData(list: List<DataWord>): List<DataWord> {
		return if (list.isEmpty()) basisData
		else list
	}

	fun getSaved() {
		val saved = ModelResult(currentData)
		if (saved.data.isEmpty()) dataLive.postValue(StatusProcess.ForceSet(repository.checkLast()))
		else dataLive.postValue(StatusProcess.Success(saved))
	}

	fun visualChange(data: DataWord, position: Int) {
		val count = if (data.visual.state == 1) {
			changeStateAt(data, position, 0)
		}
		else {
			changeStateAt(data, position, 1)
		}
		dataLive.postValue(StatusProcess.Change(currentData, position + 1, count))
	}

	private fun changeStateAt(data: DataWord, position: Int, state: Int): Int {
		currentData[position].visual.state = state
		return setState(state * 2, data.visual.group + 1)
	}

	private fun setState(state: Int, group: Int): Int {
		var count = 0
		currentData.forEach { tek ->
			if (tek.visual.group == group) {
				tek.visual.state = state
				count++
			}
		}
		return count
	}

}