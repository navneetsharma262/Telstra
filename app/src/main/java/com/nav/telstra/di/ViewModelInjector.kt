package com.nav.telstra.di

import com.nav.telstra.features.feedlist.viewModel.FeedListViewModel
import com.nav.telstra.network.RetrofitInstance
import dagger.Component
import javax.inject.Singleton

// Dependency provider
@Singleton
@Component(modules = [(RetrofitInstance::class)])
interface ViewModelInjector {

    fun inject(feedListViewModel: FeedListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: RetrofitInstance): Builder
    }
}