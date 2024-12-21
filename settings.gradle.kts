pluginManagement {
    repositories {
        // Gradle plugins, Android Gradle plugin, etc.
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        // Android Gradle Plugin and Kotlin plugin versions that work with Compose 1.5.3
        id("com.android.application") version "8.7.3"
        id("org.jetbrains.kotlin.android") version "1.9.10"
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "InfiniView"
include(":app")
