package com.InfiniumImageryLLC.infiniview.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SettingsView() {
    // Simplified example. In reality, you'd have toggles, network calls, etc.
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Settings", color = Color.White)
            Spacer(modifier = Modifier.height(24.dp))
            SettingsFooter() // from iOS-like code
        }
    }
}

@Composable
fun SettingsFooter() {
    Column {
        Text("Made in Pittsburgh, PA USA 🇺🇸", color = Color.Gray)
        Text("iVIEW Version 1.00 Build 1", color = Color.Gray)
        Text("Web Version R1.34.3", color = Color.Gray)
        Text("© 2024 Infinitum Imagery LLC & Infinitum LIVE Creator Network", color = Color.Gray)
        Text("Made By @ItsCoy & @JrFTW All Rights Reserved", color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))

        // Example links:
        Text("Privacy Policy:", color = Color.White)
        Text("    https://infinitumlive.com/privacy-policy", color = Color.Blue)
        Spacer(modifier = Modifier.height(4.dp))

        Text("Terms of Service:", color = Color.White)
        Text("    https://infinitumlive.com/terms", color = Color.Blue)
        Spacer(modifier = Modifier.height(4.dp))

        Text("Ownership:", color = Color.White)
        Text("    Show OwnershipView (not fully implemented here)", color = Color.Blue)
    }
}
