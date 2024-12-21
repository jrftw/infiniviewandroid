package com.InfiniumImageryLLC.InfiniView.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp

@Composable
fun HomeView(adEnabled: Boolean, removeAdsPurchased: Boolean) {
    Column {
        if (adEnabled && !removeAdsPurchased) {
            AdBannerView(adUnitID = "ca-app-pub-6815311336585204/9360886288")
        }
        // WebView
        WebViewScreen(url = "https://view.infinitumlive.com/")
    }
}
