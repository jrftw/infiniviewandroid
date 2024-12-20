package com.InfiniumImageryLLC.infiniview.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.InfiniumImageryLLC.infiniview.viewmodel.AchievementsViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun AchievementsScreen(vm: AchievementsViewModel = viewModel()) {
    // If achievements is LiveData<List<Achievement>>, use observeAsState:
    val achievements by vm.achievements.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        vm.refresh()
    }

    Scaffold(
        topBar = { AppTopBar(title = "Achievements") }
    ) { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                items(achievements) { achievement ->
                    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = achievement.title,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = if (achievement.isUnlocked) "Unlocked" else "Locked",
                                color = if (achievement.isUnlocked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                            )
                        }
                        Text(
                            text = achievement.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha=0.7f)
                        )
                        val progress = achievement.currentProgress.toFloat()/achievement.target.toFloat()
                        LinearProgressIndicator(
                            progress = progress.coerceIn(0f,1f),
                            modifier = Modifier.fillMaxWidth().padding(top=4.dp),
                            trackColor = if (achievement.isUnlocked) MaterialTheme.colorScheme.primary.copy(alpha=0.3f) else MaterialTheme.colorScheme.secondary.copy(alpha=0.3f),
                            color = if (achievement.isUnlocked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}
