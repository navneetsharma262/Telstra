package com.nav.telstra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nav.telstra.features.feedlist.view.FeedListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, FeedListFragment())
            .commit()
    }
}