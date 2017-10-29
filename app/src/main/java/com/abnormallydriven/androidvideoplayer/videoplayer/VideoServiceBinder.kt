package com.abnormallydriven.androidvideoplayer.videoplayer

import android.os.Binder
import com.abnormallydriven.androidvideoplayer.common.dagger.ServiceScope
import javax.inject.Inject

@ServiceScope
class VideoServiceBinder @Inject constructor(val videoService : VideoService): Binder()