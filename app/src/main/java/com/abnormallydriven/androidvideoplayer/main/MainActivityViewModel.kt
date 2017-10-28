package com.abnormallydriven.androidvideoplayer.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.abnormallydriven.androidvideoplayer.common.YoutubeApi
import com.abnormallydriven.androidvideoplayer.common.dagger.UI
import com.abnormallydriven.androidvideoplayer.common.responses.PopularVideosResponse
import com.abnormallydriven.androidvideoplayer.common.responses.Video
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainActivityViewModel @Inject constructor(private val youtubeApi: YoutubeApi,
                                                @UI private val  uiScheduler: Scheduler) : ViewModel() {

    val videoLiveData : MutableLiveData<List<Video>> = MutableLiveData()

    private val subscriptions: CompositeDisposable = CompositeDisposable()

    fun onRefreshVideoList() {
        Log.d("debug", "Loading fresh videos")

        youtubeApi.getPopularVideos()
                .map { response: PopularVideosResponse -> response.items.filterNotNull() }
                .observeOn(uiScheduler)
                .subscribe(object : SingleObserver<List<Video>>{
                    override fun onSuccess(videoList: List<Video>) {
                        videoLiveData.value = videoList
                    }

                    override fun onError(e: Throwable) {
                        Log.d("debug", "Error getting videos")
                        Log.d("debug", e.message)
                    }

                    override fun onSubscribe(d: Disposable) {
                        subscriptions.add(d)
                    }
                })
    }

    fun onVideoClicked(video: Video) {
        Log.d("debug", "Clicked Video with ID: " + video.id)
    }

    override fun onCleared() {
        subscriptions.clear()
        super.onCleared()
    }
}