package com.abnormallydriven.androidvideoplayer.videoplayer

import android.app.Service
import android.arch.lifecycle.LifecycleService
import android.content.Intent
import android.net.Uri
import android.os.IBinder
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

        //setup media session compat
        //setup the exoplayer

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        //TODO handle commands like play/pause

        return Service.START_NOT_STICKY
    }

    override fun onDestroy() {
        exoplayerController.releasePlayer()
        super.onDestroy()
    }

    fun playVideo(uri : Uri){
        exoplayerController.playVideoAtUri(uri)
    }

    fun resume(){
        exoplayerController.resume()
    }

    fun pause(){
        exoplayerController.pause()
    }
}
