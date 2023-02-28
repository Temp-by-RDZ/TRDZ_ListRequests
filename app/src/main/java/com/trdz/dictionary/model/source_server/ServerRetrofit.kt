package com.trdz.dictionary.model.source_server

import com.trdz.dictionary.model.data.DataWord
import com.trdz.dictionary.model.data.RequestResults
import com.trdz.dictionary.model.data.ServersResult
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Response

class ServerRetrofit: com.trdz.dictionary.model.data.ADataSource {

	//region Injected

	private val data: ServerRetrofitApi by inject(ServerRetrofitApi::class.java)

	//endregion

	override fun loadWords(target: String): RequestResults {
		val retrofit = data
		return try {
			val response = retrofit.getResponse(target).execute()
			RequestResults.SuccessWords(responseFormation(response))
		}
		catch (error: Exception) {
			RequestResults.Error(-1, responseFail(error))
		}
	}

	private fun responseFormation(response: Response<com.trdz.dictionary.model.source_server.dto.WordsDTO>): ServersResult {
		return if (response.isSuccessful) response.body()!!.run {
			val wordList: MutableList<DataWord> = emptyList<DataWord>().toMutableList()
			forEachIndexed { index, user ->
				val group = index * 2
				wordList.add(com.trdz.dictionary.model.source_server.ResponseMapper.mapToEntity(user, group))
				user.meanings.forEach { meaning ->
					wordList.add(com.trdz.dictionary.model.source_server.ResponseMapper.mapToEntity(meaning, group + 1))
				}

			}
			ServersResult(response.code(), dataWord = wordList)
		}
		else ServersResult(response.code())
	}

	private fun responseFail(error: Exception) = Throwable("Error code: -1, Internet connection lost or current data available:\n$error")
}
