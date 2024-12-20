package com.InfiniumImageryLLC.infiniview.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppContent() {
    val navController = rememberNavController()
    val lightColors = lightColorScheme()
    val darkColors = darkColorScheme()

    MaterialTheme(colorScheme = lightColors) {
        Surface(modifier = Modifier.fillMaxSize()) {
            NavHost(navController = navController, startDestination = "achievements") {
                composable("achievements") { AchievementsScreen() }
            }
        }
    }
}
