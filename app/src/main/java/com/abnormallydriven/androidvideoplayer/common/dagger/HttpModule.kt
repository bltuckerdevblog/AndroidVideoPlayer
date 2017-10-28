package com.abnormallydriven.androidvideoplayer.common.dagger

import com.abnormallydriven.androidvideoplayer.AndroidVideoPlayerApplication
import com.abnormallydriven.androidvideoplayer.common.YoutubeApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class HttpModule {

    private val cacheSize: Long = 10 * 1024 * 1024

    @Provides
    @Singleton
    fun provideDiskCache(androidVideoPlayerApplication: AndroidVideoPlayerApplication): Cache{
        return Cache(androidVideoPlayerApplication.cacheDir, cacheSize)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(diskCache : Cache) : OkHttpClient{
        return OkHttpClient.Builder()
                .cache(diskCache)
                .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson{
        return Gson()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson : Gson) : GsonConverterFactory{
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideRxJavaCallAdapterFactory(@IO ioScheduler: Scheduler): RxJava2CallAdapterFactory{
        return RxJava2CallAdapterFactory.createWithScheduler(ioScheduler)
    }

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory,
                        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
                        okHttpClient: OkHttpClient): Retrofit{

        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides
    @Singleton
    fun provideYoutubeApi(retrofit: Retrofit) : YoutubeApi{
        return retrofit.create(YoutubeApi::class.java)
    }

}