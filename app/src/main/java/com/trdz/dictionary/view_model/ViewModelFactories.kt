package com.trdz.dictionary.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trdz.dictionary.model.data.DataLine
import com.trdz.dictionary.model.Repository

class ViewModelFactories(
	private val repository: Repository,
	private val dataLive: SingleLiveData<StatusProcess>,
): ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T: ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
		WordsViewModel::class.java -> WordsViewModel(repository, dataLive)
		else -> throw IllegalArgumentException("Unknown ViewModel class")
	} as T

}