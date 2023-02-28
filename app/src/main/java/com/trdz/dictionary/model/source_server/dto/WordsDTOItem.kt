package com.trdz.dictionary.model.source_server.dto


import com.google.gson.annotations.SerializedName

data class WordsDTOItem(
	@SerializedName("id")
	val id: Int,
	@SerializedName("meanings")
	val meanings: List<com.trdz.dictionary.model.source_server.dto.Meaning>,
	@SerializedName("text")
	val text: String,
)