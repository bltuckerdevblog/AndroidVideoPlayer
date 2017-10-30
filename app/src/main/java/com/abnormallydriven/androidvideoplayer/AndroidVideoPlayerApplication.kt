package com.abnormallydriven.androidvideoplayer

import android.app.Activity
import android.app.Application
import android.app.Service
import com.abnormallydriven.androidvideoplayer.common.dagger.AppModule
import com.abnormallydriven.androidvideoplayer.common.dagger.ApplicationComponent
import com.abnormallydriven.androidvideoplayer.common.dagger.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import javax.inject.Inject

class AndroidVideoPlayerApplication : Application(),
        HasActivityInjector,
        HasServiceInjector {

    val appComponent: ApplicationComponent by lazy{
        DaggerApplicationComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return dispatchingServiceInjector
    }
}