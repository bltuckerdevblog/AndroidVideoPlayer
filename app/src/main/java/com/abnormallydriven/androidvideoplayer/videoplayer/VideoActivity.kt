package com.abnormallydriven.androidvideoplayer.videoplayer

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.abnormallydriven.androidvideoplayer.R
import dagger.android.AndroidInjection
import javax.inject.Inject

class VideoActivity : AppCompatActivity() {

    @Inject
    lateinit var videoServiceConnection : VideoServiceConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val videoServiceIntent = Intent(this, VideoService::class.java)
        bindService(videoServiceIntent, videoServiceConnection, Context.BIND_AUTO_CREATE)

        //read video id from intent and get ready to send it along
    }



    companion object {

        private val VIDEO_ID_INTENT_KEY = "videoId"

        fun newIntent(context: Context, videoId: String): Intent {
            val intent = Intent(context, VideoActivity::class.java)
            intent.putExtra(VIDEO_ID_INTENT_KEY, videoId)

            return intent
        }

    }

    fun onServiceConnected() {
        val videoId : String = intent.extras.getString(VIDEO_ID_INTENT_KEY, "")

        if(videoId.isEmpty()){
            return
        }

        videoServiceConnection.playVideo(videoId)

    }

    fun onServiceDisconnected() {

    }
}
