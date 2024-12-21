@file:OptIn(ExperimentalMaterial3Api::class)

package com.InfiniumImageryLLC.infiniview.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BansViolationsView(navController: NavController) {
    var showWebView by remember { mutableStateOf(false) }
    var selectedURL by remember { mutableStateOf<String?>(null) }

    val openUrl: (String) -> Unit = { url ->
        selectedURL = url
        showWebView = true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bans & Violations", color = Color.White) },
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
                .fillMaxSize()
                .padding(innerPadding),
            color = Color.Black
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Steps to appeal:",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    """
Did you actually violate community guidelines?
Did you use a word not to say?
Did someone else in your LIVE say something they were not supposed to?

If yes, do not submit an appeal with us as it will be unsuccessful.

1. Appeal In-App
2. If your appeal was successful and restored you are all set. If not, screenshot the appeal not restored and then appeal your violation with us. Check back for updates.
                    """.trimIndent(),
                    color = Color.White
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    "Open Bans & Violations Form",
                    color = Color.Blue,
                    modifier = Modifier
                        .clickable {
                            openUrl("https://docs.google.com/forms/d/e/1FAIpQLSeShd-E8wOWqUywO47-4fdfyVaNngHNNaMnFl5_XIzyeQ19gw/viewform")
                        }
                        .padding(8.dp)
                )
                Text(
                    "Open Community Guidelines Training",
                    color = Color.Blue,
                    modifier = Modifier
                        .clickable {
                            openUrl("https://docs.google.com/presentation/d/1BBD8Qy9SPjONJvZ2sVi544FOUbSMG4L_iF8Oi15tfSw/edit#slide=id.p")
                        }
                        .padding(8.dp)
                )
                Text(
                    "Open Community Guidelines Quiz",
                    color = Color.Blue,
                    modifier = Modifier
                        .clickable {
                            openUrl("https://forms.gle/2sizBroPNapbpJNp7")
                        }
                        .padding(8.dp)
                )
                Text(
                    "View 'Words Not To Say' on TikTok",
                    color = Color.Blue,
                    modifier = Modifier
                        .clickable {
                            openUrl("https://docs.google.com/document/d/1RYqzSZUJl-bd-sSjyY2Pyjfrt7ap1BPESVRV6m9m1g4/edit?usp=sharing")
                        }
                        .padding(8.dp)
                )
            }
        }
    }
}
