pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        // Adjust versions here if needed
        id("com.android.application") version "8.1.1"
        id("org.jetbrains.kotlin.android") version "1.8.22"
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
