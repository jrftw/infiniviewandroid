package com.InfiniumImageryLLC.infiniview.ui

import androidx.compose.runtime.Composable
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun WebViewScreen(url: String) {
    val state = rememberWebViewState(url = url)
    WebView(state = state)
}
