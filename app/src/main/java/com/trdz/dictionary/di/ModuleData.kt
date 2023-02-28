package com.trdz.dictionary.di

import com.google.gson.GsonBuilder
import com.trdz.dictionary.model.source_server.ServerRetrofitApi
import com.trdz.dictionary.utility.DOMAIN
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val moduleDataK = module {
	single<ServerRetrofitApi>() {
		Retrofit.Builder().apply {
			baseUrl(DOMAIN)
			addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
		}.build().create(ServerRetrofitApi::class.java)
	}

}


