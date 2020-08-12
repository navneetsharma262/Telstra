package com.nav.telstra.network

import com.nav.telstra.features.feedlist.model.FeedResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    suspend fun getFeedList(): Response<FeedResponse>
}