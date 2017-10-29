package com.abnormallydriven.androidvideoplayer.videoplayer

import android.app.Service
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ServiceKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(VideoServiceSubcomponent::class))
abstract class VideoServiceInjectorModule {

    @Binds
    @IntoMap
    @ServiceKey(VideoService::class)
    abstract fun bindInjectorFactory(builder : VideoServiceSubcomponent.Builder) : AndroidInjector.Factory<out Service>
}