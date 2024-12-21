// InfiniViewApp.kt
package com.InfiniumImageryLLC.InfiniView

import android.app.Application
import com.google.android.gms.ads.MobileAds

class InfiniViewApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize the Mobile Ads SDK
        MobileAds.initialize(this)
    }
}
