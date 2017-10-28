package com.abnormallydriven.androidvideoplayer.common.dagger

import dagger.Component

@Component(modules = arrayOf(SchedulerModule::class,
        HttpModule::class,
        AppModule::class))
interface ApplicationComponent {

}