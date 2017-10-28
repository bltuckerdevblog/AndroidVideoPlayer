package com.abnormallydriven.androidvideoplayer.main

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(MainActivitySubcomponent::class))
abstract class MainActivityInjectorModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindInjectorFactory(builder : MainActivitySubcomponent.Builder): AndroidInjector.Factory<out Activity>
}