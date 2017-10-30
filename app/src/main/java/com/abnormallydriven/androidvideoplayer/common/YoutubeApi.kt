package com.abnormallydriven.androidvideoplayer.common

import com.abnormallydriven.androidvideoplayer.common.responses.VideosResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface YoutubeApi {

    @GET("videos")
    @Headers("Cache-Control: max-age=900")
    fun getPopularVideos(): Single<VideosResponse>

}