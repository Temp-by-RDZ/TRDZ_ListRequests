package com.trdz.dictionary.model.source_server.dto


import com.google.gson.annotations.SerializedName

data class Translation(
	@SerializedName("note")
	val note: String,
	@SerializedName("text")
	val text: String,
)