package com.nav.telstra.features.feedlist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nav.telstra.R
import com.nav.telstra.features.feedlist.model.Feed
import kotlinx.android.synthetic.main.feed_item_row.view.*

class FeedListAdapter(private val feedList: ArrayList<Feed>) : RecyclerView.Adapter<FeedListAdapter.FeedViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.feed_item_row, parent, false)
        return FeedViewHolder(view)
    }

    override fun getItemCount() = feedList.size

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.view.txt_title.text = feedList[position].title
        holder.view.txt_description.text = feedList[position].description
    }


    class FeedViewHolder(var view : View) : RecyclerView.ViewHolder (view)

    fun updateFeedList(updatedDeedList: List<Feed>) {
        feedList.clear()
        feedList.addAll(updatedDeedList)
        notifyDataSetChanged()
    }

}