package com.abnormallydriven.androidvideoplayer.common

import android.content.Context
import android.support.annotation.Keep

import com.abnormallydriven.androidvideoplayer.AndroidVideoPlayerApplication
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule

import java.io.InputStream

import okhttp3.OkHttpClient

@Keep
@GlideModule
class GlideAppModule : AppGlideModule() {

    override fun registerComponents(context: Context?, glide: Glide?, registry: Registry?) {
        val okHttpClient = (context!!.applicationContext as AndroidVideoPlayerApplication).appComponent.okHttpClient()

        val factory = OkHttpUrlLoader.Factory(okHttpClient)
        registry!!.replace(GlideUrl::class.java, InputStream::class.java, factory)
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}
