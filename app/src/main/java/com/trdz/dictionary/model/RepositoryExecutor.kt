package com.trdz.dictionary.model

import com.trdz.dictionary.model.data.ADataSource
import com.trdz.dictionary.model.data.DataWord
import com.trdz.dictionary.model.data.RequestResults

class RepositoryExecutor(
	private val dataServer: ADataSource,
): Repository {

	private var lastSearch: String = ""

	override fun checkLast() = lastSearch


	//region Word Data update

	override suspend fun initWordList(target: String): RequestResults {
		lastSearch = target
		return dataServer.loadWords(target)
	}

	override suspend fun analyze(data: List<DataWord>): List<DataWord> {
		return data
	}

	//endregion

}
