package com.InfiniumImageryLLC.infiniview

import timber.log.Timber
import android.app.Application
import com.google.android.gms.ads.MobileAds

class InfiniViewApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        Timber.plant(Timber.DebugTree())
        // Initialization logic for analytics, etc.
    }
}
