package com.abnormallydriven.androidvideoplayer.common.dagger

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.abnormallydriven.androidvideoplayer.common.ApplicationViewModelFactory
import com.abnormallydriven.androidvideoplayer.main.MainActivityViewModel
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
    abstract fun bindViewModelFactory(applicationViewModelFactory: ApplicationViewModelFactory): ViewModelProvider.Factory

}