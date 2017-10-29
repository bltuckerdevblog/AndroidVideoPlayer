package com.abnormallydriven.androidvideoplayer.videoplayer

import com.abnormallydriven.androidvideoplayer.common.dagger.ServiceScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ServiceScope
interface VideoServiceSubcomponent : AndroidInjector<VideoService> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<VideoService>() {}
}