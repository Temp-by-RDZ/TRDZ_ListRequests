package com.trdz.dictionary.di

import com.trdz.dictionary.model.data.ADataSource
import com.trdz.dictionary.model.source_server.ServerRetrofit
import com.trdz.dictionary.utility.KK_SERVER
import org.koin.core.qualifier.named
import org.koin.dsl.module

val moduleRepositoryK = module {
	single<ADataSource>(named(KK_SERVER)) { ServerRetrofit() }
}


