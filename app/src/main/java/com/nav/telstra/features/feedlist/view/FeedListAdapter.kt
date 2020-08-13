package com.nav.telstra.features.feedlist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nav.telstra.R
import com.nav.telstra.features.feedlist.model.Feed
import com.nav.telstra.utils.getProgressDrawable
import com.nav.telstra.utils.loadImage
import kotlinx.android.synthetic.main.feed_item_row.view.*

class FeedListAdapter(private val feedList: ArrayList<Feed>) : RecyclerView.Adapter<FeedListAdapter.FeedViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.feed_item_row, parent, false)
        return FeedViewHolder(view)
    }

    override fun getItemCount() = feedList.size

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val feed = feedList[position]

        holder.view.txt_title.text = feed.title
        holder.view.txt_description.text = feed.description

        // Showing and loading the image only when image url is not null
        feed.image_url?.let {
            holder.view.imageView.visibility = View.VISIBLE
            holder.view.imageView.loadImage(feed.image_url, getProgressDrawable(holder.view.imageView.context))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class FeedViewHolder(var view : View) : RecyclerView.ViewHolder (view)

    fun updateFeedList(updatedDeedList: List<Feed>) {
        feedList.clear()
        feedList.addAll(updatedDeedList)
        notifyDataSetChanged()
    }

}