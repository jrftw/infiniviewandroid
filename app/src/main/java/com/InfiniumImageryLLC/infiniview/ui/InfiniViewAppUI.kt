package com.InfiniumImageryLLC.InfiniView

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.*
import com.InfiniumImageryLLC.InfiniView.ui.*

data class NavItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfiniViewAppUI() {
    val darkColors = darkColorScheme(
        primary = Color(0xFF00FF7F),
        onPrimary = Color.White,
        background = Color.Black,
        surface = Color.Black,
        onSurface = Color.White
    )

    MaterialTheme(colorScheme = darkColors) {
        val navController = rememberNavController()

        val navItems = listOf(
            NavItem("home", "Home", Icons.Filled.Home),
            NavItem("resources", "Resources", Icons.Filled.MenuBook),
            NavItem("events", "Events", Icons.Filled.CalendarMonth),
            NavItem("settings", "Settings", Icons.Filled.Settings)
        )

        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ) {
                    val currentDestination = navController.currentBackStackEntry?.destination
                    navItems.forEach { item ->
                        val selected = currentDestination?.route == item.route
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            },
                            icon = {
                                Icon(
                                    item.icon,
                                    contentDescription = item.label,
                                    tint = if (selected) Color.White else Color.Gray
                                )
                            },
                            label = { Text(item.label) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                selectedTextColor = Color.White,
                                unselectedIconColor = Color.Gray,
                                unselectedTextColor = Color.Gray
                            )
                        )
                    }
                }
            },
            containerColor = Color.Black
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("home") {
                    ScreenWithTopBar("Home") {
                        // Maybe a WebView or any home content:
                        WebViewScreen("https://view.infinitumlive.com/")
                    }
                }
                composable("resources") {
                    // We pass navController to ResourcesView
                    ResourcesView(navController)
                }
                composable("events") {
                    ScreenWithTopBar("Events") {
                        EventsView()
                    }
                }
                composable("settings") {
                    ScreenWithTopBar("Settings") {
                        // e.g. SettingsView()
                        Text("Settings screen content", color = Color.White)
                    }
                }

                // The routes from handleResourceClick:
                composable("announcements") {
                    ScreenWithTopBar("Announcements") {
                        AnnouncementsView()
                    }
                }
                composable("leaderboard") {
                    ScreenWithTopBar("Leaderboard") {
                        Text("Leaderboard Screen Goes Here", color = Color.White)
                    }
                }
                composable("achievements") {
                    ScreenWithTopBar("Achievements") {
                        Text("Achievements Screen Goes Here", color = Color.White)
                    }
                }
                composable("dip") {
                    ScreenWithTopBar("D.I.P") {
                        Text("Diamond Incentive Program Goes Here", color = Color.White)
                    }
                }
                composable("bansViolations") {
                    ScreenWithTopBar("Bans & Violations") {
                        Text("BansViolationsView Goes Here", color = Color.White)
                    }
                }
                composable("streamKeyRequests") {
                    ScreenWithTopBar("Stream Key Requests") {
                        Text("StreamKeyRequestsView Goes Here", color = Color.White)
                    }
                }
                composable("tikTokLiveStudioRequests") {
                    ScreenWithTopBar("TikTok LIVE Studio Requests") {
                        Text("TikTokLIVEStudioRequestsView Goes Here", color = Color.White)
                    }
                }
                composable("selfPacedModules") {
                    ScreenWithTopBar("Self-Paced Modules") {
                        Text("SelfPacedModulesView Goes Here", color = Color.White)
                    }
                }
                composable("roster") {
                    ScreenWithTopBar("Roster") {
                        Text("RosterView Goes Here", color = Color.White)
                    }
                }
                composable("help") {
                    ScreenWithTopBar("Help") {
                        Text("HelpView Goes Here", color = Color.White)
                    }
                }
                composable("reportAProblem") {
                    ScreenWithTopBar("Report A Problem") {
                        Text("ReportAProblemView Goes Here", color = Color.White)
                    }
                }
                composable("howTos") {
                    ScreenWithTopBar("How-To's") {
                        Text("HowTosView Goes Here", color = Color.White)
                    }
                }
                composable("save30") {
                    ScreenWithTopBar("Save 30%") {
                        Text("Save30View Goes Here", color = Color.White)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenWithTopBar(title: String, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title, color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = Color.Black
        ) {
            content()
        }
    }
}
