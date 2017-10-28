package com.abnormallydriven.androidvideoplayer.videoplayer

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(VideoActivitySubcomponent::class))
abstract class VideoActivityInjectorModule {

    @Binds
    @IntoMap
    @ActivityKey(VideoActivity::class)
    abstract fun bindInjectorFactory(builder: VideoActivitySubcomponent.Builder) : AndroidInjector.Factory<out Activity>
}