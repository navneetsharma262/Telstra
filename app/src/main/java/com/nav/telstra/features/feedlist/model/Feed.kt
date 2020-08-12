package com.nav.telstra.features.feedlist.model

import com.google.gson.annotations.SerializedName

data class Feed(
    val title: String?,
    val description: String?,
    @SerializedName("imageHref")
    val image_url: String?
)
