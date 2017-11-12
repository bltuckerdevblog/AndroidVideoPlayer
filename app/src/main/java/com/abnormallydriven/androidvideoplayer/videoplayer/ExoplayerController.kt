package com.abnormallydriven.androidvideoplayer.videoplayer

import android.net.Uri
import android.util.Log
import com.abnormallydriven.androidvideoplayer.common.dagger.ServiceScope
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import javax.inject.Inject

@ServiceScope
class ExoplayerController @Inject constructor(val simpleExoPlayer: SimpleExoPlayer,
                                              private val defaultDataSourceFactory: DefaultDataSourceFactory,
                                              private val defaultExtractorsFactory: DefaultExtractorsFactory) :
Player.EventListener{

    init {
        simpleExoPlayer.addListener(this)
    }

    fun playVideoAtUri(uri: Uri){
        val videoMediaSource = ExtractorMediaSource(uri, defaultDataSourceFactory, defaultExtractorsFactory, null, null)

        simpleExoPlayer.playWhenReady = true
        simpleExoPlayer.prepare(videoMediaSource)
    }

    fun resume(){
        simpleExoPlayer.playWhenReady = true
    }

    fun pause(){
        simpleExoPlayer.playWhenReady = false
    }

    fun stop(){
        simpleExoPlayer.stop()
    }

    fun releasePlayer(){
        simpleExoPlayer.removeListener(this)
        simpleExoPlayer.release()
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {

        var stateString = "Unknown"
        when(playbackState){
            1 -> stateString = "Idle"
            2 -> stateString = "Buffering"
            3 -> stateString = "Ready"
            4 -> stateString = "Ended"
        }

        val message = String.format("onPlayerStateChanged playWhenReady: %b , playbackState: %s", playWhenReady, stateString)
        Log.d("exoplayer", message)
    }

    override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {
    }

    override fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?) {
    }

    override fun onPlayerError(error: ExoPlaybackException?) {
    }

    override fun onLoadingChanged(isLoading: Boolean) {
    }

    override fun onPositionDiscontinuity() {
    }

    override fun onRepeatModeChanged(repeatMode: Int) {
    }

    override fun onTimelineChanged(timeline: Timeline?, manifest: Any?) {
    }

}