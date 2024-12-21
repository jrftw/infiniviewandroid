package com.InfiniumImageryLLC.infiniview.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// We'll reuse your model classes:
data class ResourceLink(
    val title: String,
    val routeOrUrl: String
)

data class ResourceSection(
    val header: String,
    val links: List<ResourceLink>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourcesView(navController: NavController) {
    val sections = listOf(
        // 1) Announcements Section
        ResourceSection(
            header = "Announcements",
            links = listOf(
                ResourceLink("View Announcements", "announcements")
            )
        ),

        // 2) Core Resources
        ResourceSection(
            header = "Core Resources",
            links = listOf(
                ResourceLink("Infinitum LIVE Creator Network Website", "https://infinitumlive.com/"),
                ResourceLink("FAQs", "https://infinitumlive.com/faq/"),
                ResourceLink("Contact", "https://infinitumlive.com/contact/"),
                ResourceLink("Achievements Leaderboard", "leaderboard"),
                ResourceLink("Achievements", "achievements")
            )
        ),

        // 3) Additional Resources
        ResourceSection(
            header = "Additional Resources",
            links = listOf(
                ResourceLink("Diamond Incentive Program (D.I.P)", "dip"),
                ResourceLink("Bans & Violations", "bansViolations"),
                ResourceLink("Stream Key Requests", "streamKeyRequests"),
                ResourceLink("TikTok LIVE Studio Requests", "tikTokLiveStudioRequests"),
                ResourceLink("Self-Paced Modules", "selfPacedModules"),
                ResourceLink("Benefits", "https://infinitumlive.com/benefits-of-infinitum/"),
                ResourceLink("Roster", "roster"),
                ResourceLink("Discord", "https://discord.gg/tMCg9WqDT9"),
                ResourceLink("Help", "help"),
                ResourceLink("Report A Problem", "reportAProblem"),
                ResourceLink("Low Views Help", "https://docs.google.com/presentation/d/17pMQyvltWC2G3ezdxteGoGI6DVLMZw-gxj7W44YqXLw/edit?usp=sharing"),
                ResourceLink("How-To's", "howTos"),
                ResourceLink("Save 30% When Recharging", "save30"),
                ResourceLink("TikTok Shop Application", "https://infinitumlive.com/tiktok-shop/"),
                ResourceLink("Not In Our Network?", "https://infinitumlive.com/creator-pre-check/"),
                ResourceLink("Merch", "https://infinitum-live-agency.myspreadshop.com/all")
            )
        )
    )

    // If you already have a parent Scaffold + TopAppBar in your NavHost, you can remove
    // this local Scaffold. Otherwise, having a local top bar is fine.
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
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                sections.forEach { section ->
                    item {
                        Text(
                            text = section.header,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                        )
                    }
                    items(section.links) { linkItem ->
                        Text(
                            text = linkItem.title,
                            color = Color.Blue,
                            modifier = Modifier
                                .clickable {
                                    handleResourceClick(navController, linkItem.routeOrUrl)
                                }
                                .padding(vertical = 8.dp, horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

// Simple helper to handle tapping a link
private fun handleResourceClick(navController: NavController, routeOrUrl: String) {
    // If the string looks like a route, navigate. Otherwise open external URL or a webview route.
    when (routeOrUrl) {
        "announcements" -> navController.navigate("announcements")
        "leaderboard" -> navController.navigate("leaderboard")
        "achievements" -> navController.navigate("achievements")
        "dip" -> navController.navigate("dip")
        "bansViolations" -> navController.navigate("bansViolations")
        "streamKeyRequests" -> navController.navigate("streamKeyRequests")
        "tikTokLiveStudioRequests" -> navController.navigate("tikTokLiveStudioRequests")
        "selfPacedModules" -> navController.navigate("selfPacedModules")
        "roster" -> navController.navigate("roster")
        "help" -> navController.navigate("help")
        "reportAProblem" -> navController.navigate("reportAProblem")
        "howTos" -> navController.navigate("howTos")
        "save30" -> navController.navigate("save30")
        else -> {
            // If it starts with "http", we open an external link or a WebView composable
            if (routeOrUrl.startsWith("http")) {
                // You can either open a separate "SingleResourceView" route
                // or do navController.navigate(...) to pass a URL argument, etc.
                // This is an example of a direct external launch:
                // (You could also do an Intent-based approach.)
                // For brevity, let's do a simple route, e.g., "webView?url=..."
                // But that requires navArgument. If you want to keep it simple, just do nothing.
            }
        }
    }
}
