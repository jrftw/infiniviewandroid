package com.InfiniumImageryLLC.InfiniView.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.InfiniumImageryLLC.InfiniView.viewmodel.LeaderboardViewModel

@Composable
fun LeaderboardScreen(vm: LeaderboardViewModel = viewModel()) {
    LaunchedEffect(Unit) {
        vm.loadLeaderboard()
    }

    val entries by vm.entries.collectAsState()

    Scaffold(
        topBar = { AppTopBar(title = "Leaderboard") }
    ) { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                itemsIndexed(entries) { index, entry ->
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                        Text("${index+1}. ${entry.username}", modifier = Modifier.weight(1f))
                        Text("${entry.points} pts")
                    }
                }
            }
        }
    }
}
