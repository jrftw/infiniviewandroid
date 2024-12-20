package com.InfiniumImageryLLC.infiniview.model

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

data class LeaderboardEntry(val id: String, val username: String, val points: Int)

class LeaderboardManager private constructor(context: Context) {
    companion object {
        @Volatile private var INSTANCE: LeaderboardManager? = null
        fun getInstance(context: Context): LeaderboardManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LeaderboardManager(context.applicationContext).also { INSTANCE = it }
            }
        }
    }

    // Placeholder: Use SharedPreferences or a remote DB
    suspend fun updateLeaderboard(username: String, points: Int) = withContext(Dispatchers.IO) {
        // Save username and points
    }

    suspend fun topEntries(limit: Int = 50): List<LeaderboardEntry> = withContext(Dispatchers.IO) {
        // Return a placeholder list
        listOf(
            LeaderboardEntry(UUID.randomUUID().toString(), "User1234", 100),
            LeaderboardEntry(UUID.randomUUID().toString(), "User5678", 80)
        )
    }
}
