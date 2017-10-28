package com.abnormallydriven.androidvideoplayer.common.responses

data class PopularVideosResponse(
        val kind: String? = null,
        val etag: String? = null,
        val items: List<Video?>
)
