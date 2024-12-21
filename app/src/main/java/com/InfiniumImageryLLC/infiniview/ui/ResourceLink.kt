package com.InfiniumImageryLLC.InfiniView.models

data class ResourceLink(
    val title: String,
    val routeOrUrl: String
)

data class ResourceSection(
    val header: String,
    val links: List<ResourceLink>
)
