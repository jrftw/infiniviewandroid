plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // (Optional) Add more if needed (e.g. Hilt, etc.)
}

android {
    namespace = "com.InfiniumImageryLLC.infiniview"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.InfiniumImageryLLC.infiniview"
        minSdk = 24
        targetSdk = 34

        versionCode = 2
        versionName = "1.01"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        // debug { ... } // Usually auto-created if not overridden
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

    composeOptions {
        // Match the Compose compiler to your Compose UI version
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
}

// For clarity, define or inline your Compose/m3 versions:
val composeUiVersion = "1.5.1"
val material3Version = "1.1.1"

dependencies {

    // Core & Lifecycle
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Activity + Jetpack Compose
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui:$composeUiVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeUiVersion")
    implementation("androidx.compose.foundation:foundation:$composeUiVersion")
    implementation("androidx.compose.runtime:runtime-livedata:$composeUiVersion")
    implementation("androidx.compose.material3:material3:$material3Version")

    // Icons
    implementation("androidx.compose.material:material-icons-extended:$composeUiVersion")

    // Navigation for Compose
    implementation("androidx.navigation:navigation-compose:2.7.2")

    // Accompanist WebView
    implementation("com.google.accompanist:accompanist-webview:0.30.1")

    // Android's Material library (classic Views)
    implementation("com.google.android.material:material:1.9.0")

    // Google Play services Ads
    implementation("com.google.android.gms:play-services-ads:22.0.0")

    // JSON serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    // Logging (Timber)
    implementation("com.jakewharton.timber:timber:5.0.1")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Debugging (Compose tooling)
    debugImplementation("androidx.compose.ui:ui-tooling:$composeUiVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeUiVersion")
}
