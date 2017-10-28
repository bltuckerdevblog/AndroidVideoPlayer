package com.abnormallydriven.androidvideoplayer

import android.app.Application
import com.abnormallydriven.androidvideoplayer.common.dagger.AppModule
import com.abnormallydriven.androidvideoplayer.common.dagger.ApplicationComponent
import com.abnormallydriven.androidvideoplayer.common.dagger.DaggerApplicationComponent

class AndroidVideoPlayerApplication : Application() {

    val appComponent: ApplicationComponent by lazy{
        DaggerApplicationComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}