package com.InfiniumImageryLLC.infiniview

import android.app.Application
import timber.log.Timber

class YourApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
