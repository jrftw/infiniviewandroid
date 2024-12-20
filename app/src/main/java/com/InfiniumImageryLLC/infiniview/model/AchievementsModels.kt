package com.InfiniumImageryLLC.infiniview.model

import kotlinx.serialization.Serializable

@Serializable
data class Achievement(
    val id: String,
    val title: String,
    val description: String,
    val points: Int,
    val type: AchievementType,
    val isUnlocked: Boolean,
    val currentProgress: Int,
    val target: Int
)

@Serializable
sealed class AchievementType {
    @Serializable
    data class Streak(val days: Int): AchievementType()
    @Serializable
    data class Holiday(val date: Long): AchievementType()
    @Serializable
    data class Custom(val action: String, val countNeeded: Int): AchievementType()
}
