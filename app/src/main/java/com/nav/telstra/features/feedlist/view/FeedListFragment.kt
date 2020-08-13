package com.nav.telstra.features.feedlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nav.telstra.R
import com.nav.telstra.features.feedlist.viewModel.FeedListViewModel
import kotlinx.android.synthetic.main.fragment_feed_list.*

class FeedListFragment : Fragment() {

    private lateinit var feedListViewModel: FeedListViewModel
    private val listAdapter = FeedListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedListViewModel =
            ViewModelProviders.of(this).get(FeedListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_feeds.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        item_swipe_to_refresh.setOnRefreshListener {
            // Code to refresh the list here.
            item_swipe_to_refresh.isRefreshing = false
            feedListViewModel.fetchFeedList()
        }


        feedListViewModel.fetchFeedList()

        observeViewModel()
    }

    private fun observeViewModel() {

        feedListViewModel.feedData.observe(viewLifecycleOwner, Observer { feedResponse ->
            feedResponse?.let {
                activity?.title = feedResponse.title
                recyclerView_feeds.visibility = View.VISIBLE
                listAdapter.updateFeedList(feedResponse.feedsList)
            }
        })

        feedListViewModel.loading.observe(viewLifecycleOwner, Observer {

            progressBar.visibility = if(it) View.VISIBLE else View.GONE

            if(it) {
                recyclerView_feeds.visibility = View.GONE
                txt_error.visibility = View.GONE
            }
        })

        feedListViewModel.errorMsg.observe(viewLifecycleOwner, Observer { errorMsg ->
            errorMsg?.let {
                txt_error.text = errorMsg
                recyclerView_feeds.visibility = View.GONE
                txt_error.visibility = View.VISIBLE
            }
        })
    }
}