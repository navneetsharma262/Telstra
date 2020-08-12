package com.nav.telstra.features.feedlist.model

import com.google.gson.annotations.SerializedName

data class FeedResponse(
    val title : String?,
    @SerializedName("rows")
    val feedsList : List<Feed>
)
