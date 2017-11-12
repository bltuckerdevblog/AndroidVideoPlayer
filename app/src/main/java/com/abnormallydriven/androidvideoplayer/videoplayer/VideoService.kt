package com.abnormallydriven.androidvideoplayer.videoplayer

import android.app.Service
import android.arch.lifecycle.LifecycleService
import android.content.Intent
import android.media.session.MediaSession
import android.net.Uri
import android.os.IBinder
import com.abnormallydriven.androidvideoplayer.BuildConfig
import dagger.android.AndroidInjection
import javax.inject.Inject

class VideoService : LifecycleService() {

    private var hasBoundConnection : Boolean = false

    @Inject
    lateinit var serviceBinder: VideoServiceBinder

    @Inject
    lateinit var exoplayerController : ExoplayerController

    @Inject
    lateinit var videoserviceLockDelegate : VideoServiceLockDelegate

    @Inject
    lateinit var mediaSession : MediaSession

    @Inject
    lateinit var videoServiceMediaSessionCallback : VideoServiceMediaSessionCallback

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        hasBoundConnection = true
        return serviceBinder
    }

    override fun onRebind(intent: Intent?) {
        hasBoundConnection = true
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        hasBoundConnection = false
        return super.onUnbind(intent)
    }

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
        lifecycle.addObserver(videoserviceLockDelegate)
        mediaSession.setCallback(videoServiceMediaSessionCallback)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return Service.START_NOT_STICKY
    }

    override fun onDestroy() {
        mediaSession.release()
        exoplayerController.releasePlayer()
        super.onDestroy()
    }

    fun playVideo(videoId: String){
        val uriString = String.format("%svideos/%s", BuildConfig.VIDEO_STREAMER_BASE_URI, videoId)
        exoplayerController.playVideoAtUri(Uri.parse(uriString))
    }

    fun resume(){
        exoplayerController.resume()
    }

    fun pause(){
        exoplayerController.pause()
    }

    fun stopVideo() {
        exoplayerController.stop()
    }
}
