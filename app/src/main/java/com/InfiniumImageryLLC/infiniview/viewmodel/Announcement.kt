package com.InfiniumImageryLLC.InfiniView.models

import kotlinx.serialization.Serializable

@Serializable
data class Announcement(
    val id: Int,
    val title: String,
    val date: String,
    val time: String,
    val description: String,
    val image: String? = null,
    val link: String? = null
)
