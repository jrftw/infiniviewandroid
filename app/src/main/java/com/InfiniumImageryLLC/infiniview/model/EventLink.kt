package com.InfiniumImageryLLC.infiniview.models

import kotlinx.serialization.Serializable

@Serializable
data class EventLink(
    val id: Int,
    val title: String,
    val url: String
)
