package com.InfiniumImageryLLC.infiniview.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.InfiniumImageryLLC.infiniview.model.LeaderboardEntry
import com.InfiniumImageryLLC.infiniview.model.LeaderboardManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LeaderboardViewModel(app: Application): AndroidViewModel(app) {
    private val manager = LeaderboardManager.getInstance(app)
    private val _entries = MutableStateFlow<List<LeaderboardEntry>>(emptyList())
    val entries: StateFlow<List<LeaderboardEntry>> get() = _entries

    fun loadLeaderboard() {
        viewModelScope.launch {
            _entries.value = manager.topEntries()
        }
    }
}
