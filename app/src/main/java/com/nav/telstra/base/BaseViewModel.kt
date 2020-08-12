package com.nav.telstra.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nav.telstra.di.DaggerViewModelInjector
import com.nav.telstra.di.ViewModelInjector
import com.nav.telstra.features.feedlist.viewModel.FeedListViewModel
import com.nav.telstra.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(RetrofitInstance)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is FeedListViewModel -> injector.inject(this)
        }
    }
}