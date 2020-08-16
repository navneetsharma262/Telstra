package com.nav.telstra

import android.app.Application
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Observer
import com.nav.telstra.features.feedlist.model.Feed
import com.nav.telstra.features.feedlist.model.FeedResponse
import com.nav.telstra.features.feedlist.view.FeedListFragment
import com.nav.telstra.features.feedlist.viewModel.FeedListViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class FeedsFragmentTest {

    private lateinit var viewModel: FeedListViewModel

    private val observerFeedData: Observer<FeedResponse> = mock()
    private val observerError: Observer<String> = mock()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var context: Application

    @Before
    fun setUp() {
        launchFragmentInContainer<FeedListFragment>()
        viewModel = FeedListViewModel(context)
    }

    @Test
    fun fetchDataSuccessTest() {

        launchFragmentInContainer<FeedListFragment>()

        val feed = Feed("Title", "Description", "url")
        val feedResponse = FeedResponse("Test", listOf(feed))
        observerFeedData.onChanged(feedResponse)

        val captor = ArgumentCaptor.forClass(FeedResponse::class.java)
        captor.run {
            verify(observerFeedData, Mockito.times(1)).onChanged(capture())
            assertEquals(feedResponse, value)
        }
    }

    @Test
    fun fetchDataErrorTest() {

        observerError.onChanged("Network error")
        val isError = "Network error"

        val errorCaptor =
            ArgumentCaptor.forClass(
                String::class.java
            )

        errorCaptor.run {
            verify(observerError, Mockito.times(1)).onChanged(capture())
            assertEquals(isError, value)
        }
    }
}