package com.InfiniumImageryLLC.InfiniView
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.InfiniumImageryLLC.InfiniView.ui.AnnouncementsView
import com.InfiniumImageryLLC.InfiniView.ui.EventsView
import com.InfiniumImageryLLC.InfiniView.ui.ResourcesView
import com.InfiniumImageryLLC.InfiniView.ui.SettingsView
import com.InfiniumImageryLLC.InfiniView.ui.ScreenWithTopBar
import com.InfiniumImageryLLC.InfiniView.ui.WebViewScreen

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
                    val currentRoute = navController.currentBackStackEntry?.destination?.route
                    navItems.forEach { item ->
                        val selected = (currentRoute == item.route)
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
                // “Home”
                composable("home") {
                    ScreenWithTopBar("Home") {
                        // A full-size WebView for the Home screen
                        WebViewScreen("https://view.infinitumlive.com/")
                    }
                }

                // “Resources”
                composable("resources") {
                    ScreenWithTopBar("Resources") {
                        ResourcesView(navController)
                    }
                }

                // “Announcements” route that the ResourcesView can navigate to
                composable("announcements") {
                    ScreenWithTopBar("Announcements") {
                        AnnouncementsView()
                    }
                }

                // “Events”
                composable("events") {
                    ScreenWithTopBar("Events") {
                        EventsView()
                    }
                }

                // “Settings”
                composable("settings") {
                    ScreenWithTopBar("Settings") {
                        SettingsView()
                    }
                }
            }
        }
    }
}
