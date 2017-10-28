package com.abnormallydriven.androidvideoplayer.common.dagger

import com.abnormallydriven.androidvideoplayer.AndroidVideoPlayerApplication
import com.abnormallydriven.androidvideoplayer.main.MainActivityInjectorModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(SchedulerModule::class,
        HttpModule::class,
        AppModule::class,
        AndroidInjectionModule::class,
        ViewModelsModule::class,
        MainActivityInjectorModule::class))
interface ApplicationComponent {
    fun inject(androidVideoPlayerApplication: AndroidVideoPlayerApplication)
}