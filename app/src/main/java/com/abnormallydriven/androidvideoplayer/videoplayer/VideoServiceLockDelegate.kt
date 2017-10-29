package com.abnormallydriven.androidvideoplayer.videoplayer

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.net.wifi.WifiManager
import android.os.PowerManager
import com.abnormallydriven.androidvideoplayer.common.dagger.ServiceScope
import javax.inject.Inject

@ServiceScope
class VideoServiceLockDelegate @Inject constructor(private val wifiLock: WifiManager.WifiLock,
                                                   private val wakeLock: PowerManager.WakeLock): LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        if(!wifiLock.isHeld){
            wifiLock.acquire()
        }

        if(!wakeLock.isHeld){
            wakeLock.acquire()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        if(wifiLock.isHeld){
            wifiLock.release()
        }

        if(wakeLock.isHeld){
            wakeLock.release()
        }
    }

}