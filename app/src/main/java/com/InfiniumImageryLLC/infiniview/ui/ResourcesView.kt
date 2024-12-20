// ResourcesView.kt
package com.InfiniumImageryLLC.infiniview.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class ResourceLink(
    val title: String,
    val destinationRoute: String? = null,
    val url: String? = null
)

data class ResourceSection(
    val header: String,
    val links: List<ResourceLink>
)

@Composable
fun ResourcesView(navController: NavController) {
    val announcementsSection = ResourceSection(
        header = "Announcements",
        links = listOf(
            ResourceLink(title = "View Announcements", destinationRoute = "announcements")
        )
    )

    val coreResourcesSection = ResourceSection(
        header = "Core Resources",
        links = listOf(
            ResourceLink(title = "Infinitum LIVE Creator Network Website", url = "https://infinitumlive.com/"),
            ResourceLink(title = "FAQs", url = "https://infinitumlive.com/faq/"),
            ResourceLink(title = "Contact", url = "https://infinitumlive.com/contact/"),
            ResourceLink(title = "Achievements Leaderboard", destinationRoute = "singleResource"), // or any
            ResourceLink(title = "Achievements", destinationRoute = "singleResource") // or any
        )
    )

    val additionalResourcesSection = ResourceSection(
        header = "Additional Resources",
        links = listOf(
            ResourceLink(title = "Diamond Incentive Program (D.I.P)", destinationRoute = "dip"),
            ResourceLink(title = "Bans & Violations", destinationRoute = "singleResource"),
            ResourceLink(title = "Stream Key Requests", destinationRoute = "singleResource"),
            ResourceLink(title = "TikTok LIVE Studio Requests", destinationRoute = "singleResource"),
            ResourceLink(title = "Self-Paced Modules", destinationRoute = "singleResource"),
            ResourceLink(title = "Benefits", url = "https://infinitumlive.com/benefits-of-infinitum/"),
            ResourceLink(title = "Roster", destinationRoute = "singleResource"),
            ResourceLink(title = "Discord", url = "https://discord.gg/tMCg9WqDT9"),
            ResourceLink(title = "Help", destinationRoute = "singleResource"),
            ResourceLink(title = "Report A Problem", destinationRoute = "singleResource"),
            ResourceLink(title = "Low Views Help", url = "https://docs.google.com/presentation/..."),
            ResourceLink(title = "How-To's", destinationRoute = "singleResource"),
            ResourceLink(title = "Save 30% When Recharging", destinationRoute = "singleResource"),
            ResourceLink(title = "TikTok Shop Application", url = "https://infinitumlive.com/tiktok-shop/"),
            ResourceLink(title = "Not In Our Network?", url = "https://infinitumlive.com/creator-pre-check/"),
            ResourceLink(title = "Merch", url = "https://infinitum-live-agency.myspreadshop.com/all")
        )
    )

    val sections = listOf(announcementsSection, coreResourcesSection, additionalResourcesSection)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Resources", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color.Black
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                sections.forEach { section ->
                    item {
                        Text(
                            text = section.header,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                        )
                    }
                    items(section.links) { link ->
                        Text(
                            text = link.title,
                            color = Color.Blue,
                            modifier = Modifier
                                .clickable {
                                    if (link.destinationRoute != null) {
                                        navController.navigate(link.destinationRoute)
                                    } else if (link.url != null) {
                                        navController.navigate("webView")
                                    }
                                }
                                .padding(vertical = 8.dp, horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}
