// MainActivity.kt
package com.InfiniumImageryLLC.infiniview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.InfiniumImageryLLC.infiniview.ui.ResourcesView
import com.InfiniumImageryLLC.infiniview.ui.SingleResourceView
import com.InfiniumImageryLLC.infiniview.ui.WebViewScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InfiniViewAppUI()
        }
    }
}

data class NavItem(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

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
                        val selected = currentRoute == item.route
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(0) // simpler approach
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
                    ScreenWithTopBar(title = "Home") {
                        WebViewScreen("https://view.infinitumlive.com/")
                    }
                }
                composable("resources") {
                    ScreenWithTopBar(title = "Resources") {
                        ResourcesView(navController)
                    }
                }
                composable("events") {
                    ScreenWithTopBar(title = "Events") {
                        Text("Events screen content", color = Color.White)
                    }
                }
                composable("settings") {
                    ScreenWithTopBar(title = "Settings") {
                        Text("Settings screen content", color = Color.White)
                    }
                }
                composable("singleResource") {
                    ScreenWithTopBar(title = "Single Resource") {
                        SingleResourceView(navController, title = "Any", url = "https://example.com")
                    }
                }
                composable("webView") {
                    ScreenWithTopBar(title = "WebView") {
                        WebViewScreen("https://example.com")
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenWithTopBar(title: String, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title, color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(), // no fillMaxSize if it's causing issues
            color = Color.Black
        ) {
            content()
        }
    }
}
