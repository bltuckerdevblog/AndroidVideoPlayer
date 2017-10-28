package com.abnormallydriven.androidvideoplayer.common.dagger

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class SchedulerModule {

    @Singleton
    @Provides
    @IO
    fun provideIoScheduler() : Scheduler{
        return Schedulers.io()
    }

    @Singleton
    @Provides
    @UI
    fun provideUiScheduler(): Scheduler{
        return AndroidSchedulers.mainThread()
    }

}