package com.abnormallydriven.androidvideoplayer.common.dagger

import com.abnormallydriven.androidvideoplayer.AndroidVideoPlayerApplication
import com.abnormallydriven.androidvideoplayer.main.MainActivityInjectorModule
import com.abnormallydriven.androidvideoplayer.videoplayer.VideoActivityInjectorModule
import com.abnormallydriven.androidvideoplayer.videoplayer.VideoServiceInjectorModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(SchedulerModule::class,
        HttpModule::class,
        AppModule::class,
        AndroidInjectionModule::class,
        ViewModelsModule::class,
        MainActivityInjectorModule::class,
        VideoActivityInjectorModule::class,
        VideoServiceInjectorModule::class))
interface ApplicationComponent {
    fun inject(androidVideoPlayerApplication: AndroidVideoPlayerApplication)
}