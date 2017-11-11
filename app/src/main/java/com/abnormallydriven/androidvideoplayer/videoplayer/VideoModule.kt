package com.abnormallydriven.androidvideoplayer.videoplayer

import android.media.session.MediaSession
import com.abnormallydriven.androidvideoplayer.AndroidVideoPlayerApplication
import com.abnormallydriven.androidvideoplayer.common.dagger.ServiceScope
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSourceFactory
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@ServiceScope
@Module
class VideoModule {

    @Provides
    @ServiceScope
    fun provideMediaSession(videoService: VideoService): MediaSession {
        return MediaSession(videoService, "VideoService")
    }

    @Provides
    @ServiceScope
    fun provideSimpleExoPlayer(videoPlayerApplication: AndroidVideoPlayerApplication,
                               trackSelector: TrackSelector) : SimpleExoPlayer {
        val simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(videoPlayerApplication, trackSelector)
        simpleExoPlayer.playWhenReady = true
        return simpleExoPlayer
    }

    @Provides
    @ServiceScope
    fun provideTrackSelector(adaptiveTrackSelectionFactory: AdaptiveTrackSelection.Factory): TrackSelector{
        return DefaultTrackSelector(adaptiveTrackSelectionFactory)
    }


    @Provides
    @ServiceScope
    fun provideAdaptiveTrackSelectionFactory(defaultBandwidthMeter: DefaultBandwidthMeter): AdaptiveTrackSelection.Factory {
        return AdaptiveTrackSelection.Factory(defaultBandwidthMeter)
    }


    @Provides
    @ServiceScope
    fun provideDataSourceFactory(videoPlayerApplication: AndroidVideoPlayerApplication,
                                 defaultBandwidthMeter: DefaultBandwidthMeter,
                                 okHttpDataSourceFactory: OkHttpDataSourceFactory): DefaultDataSourceFactory {
        return DefaultDataSourceFactory(videoPlayerApplication, defaultBandwidthMeter, okHttpDataSourceFactory)
    }

    @Provides
    @ServiceScope
    fun provideOkHttpDataSourceFactory(bandwidthMeter: DefaultBandwidthMeter,
                                       okHttpClient: OkHttpClient): OkHttpDataSourceFactory {
        return OkHttpDataSourceFactory(okHttpClient, null, bandwidthMeter)
    }

    @Provides
    @ServiceScope
    fun provideBandwidthMeter(): DefaultBandwidthMeter {
        return DefaultBandwidthMeter()
    }

    @Provides
    @ServiceScope
    fun provideDefaultExtractorsFactory(): DefaultExtractorsFactory {
        return DefaultExtractorsFactory()
    }

}