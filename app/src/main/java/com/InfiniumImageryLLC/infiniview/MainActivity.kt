package com.InfiniumImageryLLC.InfiniView

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Calls our top-level composable from InfiniViewAppUI.kt
            InfiniViewAppUI()
        }
    }
}
