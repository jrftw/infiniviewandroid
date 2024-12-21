package com.InfiniumImageryLLC.InfiniView.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.InfiniumImageryLLC.InfiniView.viewmodel.InfinitumSaveFeaturesViewModel

@Composable
fun InfinitumSaveFeaturesScreen(vm: InfinitumSaveFeaturesViewModel = viewModel()) {
    val isSubscribed by vm.isSubscribed.collectAsState()
    val folders by vm.folders.collectAsState()
    val isConnectedToTikTok by vm.isConnectedToTikTok.collectAsState()
    val connectedUsername by vm.connectedUsername.collectAsState()
    val currentVideoURL by vm.currentVideoURL.collectAsState()

    LaunchedEffect(Unit) {
        vm.checkSubscriptionStatus()
        vm.loadData()
    }

    Scaffold(
        topBar = { AppTopBar(title = "Infinitum Save Features") }
    ) { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            if (!isSubscribed) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Subscribe to unlock Infinitum Save Features")
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(onClick = { vm.purchaseSubscription() }) {
                        Text("Subscribe")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(onClick = { vm.restorePurchases() }) {
                        Text("Restore Purchases")
                    }
                }
            } else {
                // Subscribed UI
                Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    if (!isConnectedToTikTok) {
                        Button(onClick = { vm.connectWithTikTok() }) {
                            Text("Connect with TikTok")
                        }
                    } else {
                        Text("Connected to TikTok as: $connectedUsername")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = currentVideoURL,
                        onValueChange = { vm.updateVideoURL(it) },
                        label = { Text("TikTok video URL") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(onClick = { vm.saveAndDownloadCurrentVideo() }) {
                        Text("Save & Download Video")
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    var newFolderName by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = newFolderName,
                        onValueChange = { newFolderName = it },
                        label = { Text("New Folder Name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(onClick = {
                        if (newFolderName.isNotBlank()) {
                            vm.createFolder(newFolderName)
                            newFolderName = ""
                        }
                    }) {
                        Text("Create Folder")
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    LazyColumn {
                        items(folders.size) { i ->
                            val folder = folders[i]
                            Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                                Text(folder.name, style = MaterialTheme.typography.titleMedium)
                                folder.videos.forEach { video ->
                                    Text("${video.title}: ${video.url}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
