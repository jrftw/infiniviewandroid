// app/build.gradle.kts
plugins {
    // For an Android application:
    id("com.android.application")

    // Kotlin Android plugin:
    kotlin("android")

    // Kotlin serialization plugin (version pinned at 1.9.10):
    kotlin("plugin.serialization") version "1.9.10"
}

android {
    namespace = "com.InfiniumImageryLLC.InfiniView"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.InfiniumImageryLLC.InfiniView"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "1.01"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        // (debug is created automatically)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    // Match Compose compiler with Kotlin 1.9.x → Compose Compiler 1.5.3
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
}

// Adjust these versions as needed:
val composeUiVersion = "1.5.1"
val material3Version = "1.1.1"
val coilVersion = "2.3.0"
val okHttpVersion = "4.10.0"

dependencies {

    // Core & Lifecycle
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Compose
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui:$composeUiVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeUiVersion")
    implementation("androidx.compose.foundation:foundation:$composeUiVersion")
    implementation("androidx.compose.runtime:runtime-livedata:$composeUiVersion")
    implementation("androidx.compose.material3:material3:$material3Version")

    // Icons
    implementation("androidx.compose.material:material-icons-extended:$composeUiVersion")

    // Navigation (Compose)
    implementation("androidx.navigation:navigation-compose:2.7.2")

    // Accompanist WebView
    implementation("com.google.accompanist:accompanist-webview:0.30.1")

    // Material design library (classic Views)
    implementation("com.google.android.material:material:1.9.0")

    // Google Play services Ads
    implementation("com.google.android.gms:play-services-ads:22.0.0")

    // Kotlin Serialization JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    // Logging (e.g., Timber)
    implementation("com.jakewharton.timber:timber:5.0.1")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:$coilVersion")

    // OkHttp for networking
    implementation("com.squareup.okhttp3:okhttp:$okHttpVersion")

    // Debugging (Compose)
    debugImplementation("androidx.compose.ui:ui-tooling:$composeUiVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeUiVersion")
}
