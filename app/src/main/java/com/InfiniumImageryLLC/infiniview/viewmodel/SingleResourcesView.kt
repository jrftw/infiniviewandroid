// SingleResourceView.kt
package com.InfiniumImageryLLC.infiniview.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun SingleResourceView(
    title: String,
    url: String
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {
            // In a simpler approach (no navArguments), open external logic or do nothing special
            // Or if you have a navController in scope, navigate to "webView" or open in browser
        }) {
            Text(text = "Open $title")
        }
        Text("This resource link: $url", color = Color.White)
    }
}
