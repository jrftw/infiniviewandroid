// SingleResourceView.kt
package com.InfiniumImageryLLC.infiniview.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SingleResourceView(
    navController: NavController,
    title: String,
    url: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title, color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            color = Color.Black
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Button(onClick = {
                    navController.navigate("webView")
                }) {
                    Text("Open $title")
                }
                Text("Click the button above to open", color = Color.White)
            }
        }
    }
}
