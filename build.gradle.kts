// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    // NOTE: Do NOT apply the com.android.application plugin here—apply it in the module build.gradle.kts
    // The Android Gradle plugin should only be applied in module-level scripts (app module), not project-level.
    // Instead, you may apply other plugins for code quality, versioning, etc., if needed.
    // For typical Compose projects, it's often minimal here.
    id("org.jetbrains.kotlin.jvm") version "1.8.10" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    // Additional plugins (versions can differ):
    // id("com.android.library") version "x.x.x" apply false
    // id("com.google.gms.google-services") version "x.x.x" apply false
    // etc.
}

allprojects {
    repositories {
        google()
        mavenCentral()
        // Add other repositories if needed (e.g. jitpack, etc.)
    }
}
