package com.abnormallydriven.androidvideoplayer.common.dagger

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.abnormallydriven.androidvideoplayer.common.ApplicationViewModelFactory
import com.abnormallydriven.androidvideoplayer.main.MainActivityViewModel
import com.abnormallydriven.androidvideoplayer.videoplayer.VideoActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VideoActivityViewModel::class)
    abstract fun bindVideoActivityViewModel(videoActivityViewModel: VideoActivityViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(applicationViewModelFactory: ApplicationViewModelFactory): ViewModelProvider.Factory

}