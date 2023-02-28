package com.trdz.dictionary.model.source_server

import com.trdz.dictionary.model.data.DataWord
import com.trdz.dictionary.model.data.VisualState
import com.trdz.dictionary.model.source_server.dto.Meaning
import com.trdz.dictionary.model.source_server.dto.WordsDTOItem
import com.trdz.dictionary.utility.TYPE_CARD
import com.trdz.dictionary.utility.TYPE_TITLE

object ResponseMapper {
	fun mapToEntity(dto: com.trdz.dictionary.model.source_server.dto.WordsDTOItem, index: Int): DataWord {
		val trn = (dto.meanings.lastOrNull()?.transcription ?: "")
		return with(dto) {
			DataWord(
				id = id,
				name = text,
				subName = trn,
				iconUrl = "",
				visual = VisualState(
					type = TYPE_TITLE,
					group = index,
					state = 1)
			)
		}
	}

	fun mapToEntity(dto: com.trdz.dictionary.model.source_server.dto.Meaning, index: Int): DataWord {
		return with(dto) {
			DataWord(
				id = id,
				name = translation.text,
				subName = translation.note ?: "",
				iconUrl = imageUrl,
				visual = VisualState(
					type = TYPE_CARD,
					group = index,
					state = 2)
			)
		}
	}
}
