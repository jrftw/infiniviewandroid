package com.InfiniumImageryLLC.InfiniView.ui

import android.widget.FrameLayout
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.unit.dp
import com.google.android.gms.ads.*

@Composable
fun AdBannerView(adUnitID: String) {
    val context = LocalContext.current
    var adView by remember { mutableStateOf<AdView?>(null) }

    DisposableEffect(Unit) {
        MobileAds.initialize(context) {}
        onDispose { }
    }

    AndroidView(
        modifier = Modifier
            .wrapContentWidth()
            .height(50.dp),
        factory = { ctx ->
            AdView(ctx).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = adUnitID
                loadAd(AdRequest.Builder().build())
                adView = this
            }
        }
    )
}
