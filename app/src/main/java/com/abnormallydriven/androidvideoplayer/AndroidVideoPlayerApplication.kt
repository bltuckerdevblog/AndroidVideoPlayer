package com.abnormallydriven.androidvideoplayer

import android.app.Activity
import android.app.Application
import com.abnormallydriven.androidvideoplayer.common.dagger.AppModule
import com.abnormallydriven.androidvideoplayer.common.dagger.ApplicationComponent
import com.abnormallydriven.androidvideoplayer.common.dagger.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class AndroidVideoPlayerApplication : Application(), HasActivityInjector {

    val appComponent: ApplicationComponent by lazy{
        DaggerApplicationComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
}