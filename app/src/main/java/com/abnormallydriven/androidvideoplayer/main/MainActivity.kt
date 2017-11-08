package com.abnormallydriven.androidvideoplayer.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.abnormallydriven.androidvideoplayer.R
import com.abnormallydriven.androidvideoplayer.common.responses.Video
import com.abnormallydriven.androidvideoplayer.videoplayer.VideoActivity
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var videoAdapter: VideoAdapter

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    lateinit var viewModel : MainActivityViewModel

    private val compositeSubscriptions : CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
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

    override fun onResume() {
        super.onResume()
        viewModel.getObservableVideoClicks()
                .subscribe(object : io.reactivex.Observer<Video>{
                    override fun onComplete() {
                    }

                    override fun onNext(video: Video) {
                        val videoActivityIntent = VideoActivity.newIntent(this@MainActivity, video.id)
                        startActivity(videoActivityIntent)
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onSubscribe(d: Disposable) {
                        compositeSubscriptions.add(d)
                    }

                })
    }

    override fun onPause() {
        compositeSubscriptions.clear()
        super.onPause()
    }

    override fun onStop() {
        videoRecyclerView.adapter = null
        super.onStop()
    }
}
