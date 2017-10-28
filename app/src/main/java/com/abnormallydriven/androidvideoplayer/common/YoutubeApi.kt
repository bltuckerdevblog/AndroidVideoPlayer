package com.abnormallydriven.androidvideoplayer.common

import com.abnormallydriven.androidvideoplayer.BuildConfig
import com.abnormallydriven.androidvideoplayer.common.responses.PopularVideosResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface YoutubeApi {

    @GET("videos/?regionCode=US&chart=mostPopular&part=snippet&key=" + BuildConfig.YOUTUBE_API_KEY)
    @Headers("Cache-Control: max-age=900")
    fun getPopularVideos(): Single<PopularVideosResponse>

}