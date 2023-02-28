package com.trdz.dictionary.model

import com.trdz.dictionary.model.data.DataLine
import com.trdz.dictionary.model.data.DataWord
import com.trdz.dictionary.model.data.RequestResults

interface Repository {
	fun checkLast(): String

	//Data Word
	suspend fun initWordList(target: String): RequestResults
	suspend fun analyze(data: List<DataWord>): List<DataWord>
}