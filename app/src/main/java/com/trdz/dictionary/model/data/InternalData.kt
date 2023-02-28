package com.trdz.dictionary.model.data

interface InternalData {
	fun saveWords(list: List<DataWord>, search: String)
	fun saveFavor(list: List<com.trdz.dictionary.model.data.DataLine>)
	fun loadFavor(): List<com.trdz.dictionary.model.data.DataLine>
	fun addFavorite(data: com.trdz.dictionary.model.data.DataLine)
	fun removeFavorite(data: com.trdz.dictionary.model.data.DataLine)
	fun saveHistory(search: com.trdz.dictionary.model.data.DataLine)
	fun loadHistory(): List<com.trdz.dictionary.model.data.DataLine>
}