package com.abnormallydriven.androidvideoplayer.videoplayer

import com.abnormallydriven.androidvideoplayer.common.dagger.ActivityScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
@ActivityScope
interface VideoActivitySubcomponent : AndroidInjector<VideoActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<VideoActivity>() {}
}