package com.abnormallydriven.androidvideoplayer.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.abnormallydriven.androidvideoplayer.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var videoAdapter: VideoAdapter

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    lateinit var viewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        videoRecyclerView.adapter = videoAdapter

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)

        viewModel.videoLiveData.observe(this, Observer {
            videoList ->
            if (videoList != null) {
                videoAdapter.updateVideos(videoList)
            }
        })

        viewModel.onRefreshVideoList()
    }

    override fun onDestroy() {
        videoRecyclerView.adapter = null
        super.onDestroy()
    }
}
