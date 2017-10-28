package com.abnormallydriven.androidvideoplayer.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.abnormallydriven.androidvideoplayer.R
import com.abnormallydriven.androidvideoplayer.common.dagger.ActivityScope
import com.abnormallydriven.androidvideoplayer.common.responses.Video
import com.bumptech.glide.Glide
import javax.inject.Inject

@ActivityScope
class VideoAdapter @Inject constructor(private val mainActivityViewModel: MainActivityViewModel) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private val videoList: MutableList<Video>

    init {
        videoList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VideoViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val itemView = layoutInflater.inflate(R.layout.home_adapter_video_item, parent, false)
        val videoViewHolder = VideoViewHolder(itemView)

        itemView.setOnClickListener {
            val video = videoList[videoViewHolder.adapterPosition]
            mainActivityViewModel.onVideoClicked(video)
        }

        return videoViewHolder
    }

    override fun onBindViewHolder(holder: VideoViewHolder?, position: Int) {
        holder?.bind(videoList[position])
    }

    override fun onViewRecycled(holder: VideoViewHolder?) {
        holder?.unbind()
    }

    override fun getItemCount(): Int {
        return videoList.size
    }


    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val videoTitleTextView : TextView = itemView.findViewById(R.id.video_title_text_view)
        private val videoImageThumbnail : ImageView = itemView.findViewById(R.id.video_thumbnail_image_view)

        fun bind(video: Video) {
            videoTitleTextView.text = video.snippet.title

            Glide.with(videoImageThumbnail)
                    .load(video.snippet.thumbnails.default.url)
                    .into(videoImageThumbnail)

        }

        fun unbind(){
            Glide.with(videoImageThumbnail).clear(videoTitleTextView)
        }

    }

    fun updateVideos(updatedVideos: List<Video>) {
        videoList.clear()
        videoList.addAll(updatedVideos)
        notifyDataSetChanged()
    }
}