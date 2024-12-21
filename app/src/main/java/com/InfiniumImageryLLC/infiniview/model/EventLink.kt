package com.InfiniumImageryLLC.InfiniView.models

import kotlinx.serialization.Serializable

@Serializable
data class EventLink(
    val id: Int,
    val title: String,
    val url: String
)
