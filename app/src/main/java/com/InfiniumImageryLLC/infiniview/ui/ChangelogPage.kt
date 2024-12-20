package com.InfiniumImageryLLC.infiniview.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChangelogPage() {
    Scaffold(
        topBar = { AppTopBar("Changelog") }
    ) { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text("Changelog")
                Spacer(modifier = Modifier.height(10.dp))
                Text("Version 1.01 Build 1: Activated achievements")
                // ... add the rest of the changelog text here as needed ...
            }
        }
    }
}
