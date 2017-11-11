package com.abnormallydriven.androidvideoplayer.videoplayer

import android.media.session.MediaSession
import com.abnormallydriven.androidvideoplayer.common.dagger.ServiceScope
import javax.inject.Inject

@ServiceScope
class VideoServiceMediaSessionCallback @Inject constructor(private val videoService: VideoService) : MediaSession.Callback() {

    override fun onPlay() {
        videoService.resume()
    }

    override fun onPause() {
        videoService.pause()
    }

    override fun onStop() {
        videoService.stopVideo()
    }
}