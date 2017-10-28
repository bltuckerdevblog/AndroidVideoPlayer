package com.abnormallydriven.androidvideoplayer.main

import com.abnormallydriven.androidvideoplayer.common.dagger.ActivityScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
@ActivityScope
interface MainActivitySubcomponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>() {}

}