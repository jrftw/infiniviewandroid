package com.InfiniumImageryLLC.infiniview.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OwnershipView() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                """
                (Here you’d paste your official ownership text, 
                such as the iOS version's "OwnershipView" statement.)
                """.trimIndent(),
                color = Color.White
            )
        }
    }
}
