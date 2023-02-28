package com.trdz.dictionary.model.data

data class ServersResult(val code: Int, val dataWord: List<DataWord>? = null)

sealed class RequestResults {
	data class SuccessWords(val data: ServersResult): RequestResults()
	data class SuccessFavors(val data: List<com.trdz.dictionary.model.data.DataLine>): RequestResults()
	data class Error(val code: Int, val error: Throwable): RequestResults()
}