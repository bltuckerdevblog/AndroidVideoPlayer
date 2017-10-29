package com.abnormallydriven.androidvideoplayer.common.dagger

import android.content.Context
import android.media.AudioManager
import android.net.wifi.WifiManager
import android.os.PowerManager
import com.abnormallydriven.androidvideoplayer.AndroidVideoPlayerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(val androidVideoPlayerApplication: AndroidVideoPlayerApplication){

    @Provides
    @Singleton
    fun provideApplication() : AndroidVideoPlayerApplication{
        return androidVideoPlayerApplication
    }

    @Provides
    @Singleton
    fun provideAudioManager(): AudioManager{
        return androidVideoPlayerApplication.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    }

    @Provides
    @Singleton
    fun providePowerManager() : PowerManager{
        return androidVideoPlayerApplication.getSystemService(Context.POWER_SERVICE) as PowerManager
    }

    @Provides
    @Singleton
    fun provideWifiManager(): WifiManager{
        return androidVideoPlayerApplication.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    @Provides
    @Singleton
    fun provideWifiLock(wifiManager: WifiManager): WifiManager.WifiLock{
        return wifiManager.createWifiLock(WifiManager.WIFI_MODE_FULL_HIGH_PERF, "com.abnormallydriven.androidvideoplayer.PLAYER_WIFI_LOCK")
    }

    @Provides
    @Singleton
    fun provideWakeLock(powerManager: PowerManager): PowerManager.WakeLock{
        return powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "com.abnormallydriven.androidvideoplayer.PLAYER_WAKE_LOCK")
    }
}