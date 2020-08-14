package com.nav.telstra.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nav.telstra.R

fun getProgressDrawable(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri : String?, drawable: CircularProgressDrawable) {
    val option = RequestOptions()
        .placeholder(drawable)
        .error(R.mipmap.ic_launcher_round)
    Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(uri)
        .into(this)
}

fun isNetworkConnected(context: Context?) : Boolean {
    val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnected == true
}