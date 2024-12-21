package com.InfiniumImageryLLC.InfiniView.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp  // Add this import

@Composable
fun DisclaimerView(isDisclaimerAccepted: Boolean, onAccept: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Disclaimer", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(10.dp))
        Text("This app is designed for Infinitum LIVE Creator Network Creators...")

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { onAccept() }) {
            Text("I Understand")
        }
    }
}
