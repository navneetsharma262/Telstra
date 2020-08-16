package com.nav.telstra.features.feedlist.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.nav.telstra.base.BaseViewModel
import com.nav.telstra.features.feedlist.model.FeedResponse
import com.nav.telstra.network.ApiService
import kotlinx.coroutines.*
import javax.inject.Inject

class FeedListViewModel(application: Application) : BaseViewModel(application) {

    @Inject
    lateinit var apiService: ApiService

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        print("Error: ${throwable.localizedMessage}")
        loading.postValue(false)
        errorMsg.postValue(throwable.localizedMessage)
    }

    val errorMsg = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val feedData = MutableLiveData<FeedResponse>()

    fun fetchFeedList() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val apiResponse = apiService.getFeedList()
            withContext(Dispatchers.Main) {
                if (apiResponse.isSuccessful) {
                    loading.value = false
                    feedData.value = apiResponse.body()
                } else {
                    print(apiResponse.errorBody())
                    errorMsg.value = apiResponse.errorBody().toString()
                }
            }
        }
    }
}