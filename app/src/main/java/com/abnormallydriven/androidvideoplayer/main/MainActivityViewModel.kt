package com.abnormallydriven.androidvideoplayer.main

import android.arch.lifecycle.ViewModel
import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainActivityViewModel @Inject constructor() : ViewModel() {
    fun onRefreshVideoList() {
        Log.d("debug", "Loading fresh videos")
    }


}