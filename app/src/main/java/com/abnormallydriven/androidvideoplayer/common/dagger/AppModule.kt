package com.abnormallydriven.androidvideoplayer.common.dagger

import com.abnormallydriven.androidvideoplayer.AndroidVideoPlayerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(val androidVideoPlayerApplication: AndroidVideoPlayerApplication){

    @Provides
    @Singleton
    fun provideApplication() : AndroidVideoPlayerApplication{
        return androidVideoPlayerApplication
    }

}