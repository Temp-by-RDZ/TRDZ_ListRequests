package com.trdz.dictionary.di

import com.trdz.dictionary.model.data.DataLine
import com.trdz.dictionary.model.Repository
import com.trdz.dictionary.model.RepositoryExecutor
import com.trdz.dictionary.utility.*
import com.trdz.dictionary.view_model.SingleLiveData
import com.trdz.dictionary.view_model.StatusProcess
import com.trdz.dictionary.view_model.ViewModelFactories
import org.koin.core.qualifier.named
import org.koin.dsl.module

val moduleViewModelK = module {
	single<Repository>() {
		RepositoryExecutor(
			dataServer = get(named(KK_SERVER)),
		)
	}
	single<SingleLiveData<StatusProcess>>(named(KK_DLBASIC)) { SingleLiveData() }
	single<SingleLiveData<List<DataLine>>>(named(KK_DLLINE)) { SingleLiveData() }
	single<ViewModelFactories>() {
		ViewModelFactories(
			repository = get(),
			dataLive = get(named(KK_DLBASIC)),
		)
	}
}


