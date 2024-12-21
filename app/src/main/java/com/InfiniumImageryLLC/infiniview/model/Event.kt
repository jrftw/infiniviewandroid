package com.InfiniumImageryLLC.infiniview.models

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val id: Int,
    val title: String,
    val startDate: String,
    val endDate: String,
    val description: String,
    val imageURL: String? = null,
    val links: List<EventLink>? = null,
    val isCompleted: Boolean
)
