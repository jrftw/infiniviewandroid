package com.InfiniumImageryLLC.InfiniView.ui

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
import com.InfiniumImageryLLC.InfiniView.models.ResourceLink
import com.InfiniumImageryLLC.InfiniView.models.ResourceSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourcesView(navController: NavController) {

    // Hard-coded sections:
    val sections = listOf(
        ResourceSection(
            header = "Announcements",
            links = listOf(
                ResourceLink("View Announcements", "announcements")
            )
        ),
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

private fun handleResourceClick(navController: NavController, routeOrUrl: String) {
    when (routeOrUrl) {
        // Composable routes:
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
            // If it's an HTTP link, we can open externally or navigate to a single-URL composable
            if (routeOrUrl.startsWith("http")) {
                // Example: open a SingleResource composable if you want an in-app WebView
                // or open external browser. Up to you.
            }
        }
    }
}
