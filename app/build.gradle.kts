plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
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
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    val composeUiVersion = "1.5.1"
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui:$composeUiVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeUiVersion")
    implementation("androidx.compose.material3:material3:1.1.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.navigation:navigation-compose:2.7.2")
    implementation("androidx.compose.runtime:runtime-livedata:$composeUiVersion")
    implementation("com.google.accompanist:accompanist-webview:0.30.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("com.google.android.gms:play-services-ads:22.0.0")
    implementation("androidx.compose.material3:material3:<latest-version>")
    implementation("androidx.compose.runtime:runtime-livedata:<latest-version>")


    // JSON serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    // Logging
    implementation("com.jakewharton.timber:timber:5.0.1")

    // DataStore or SharedPreferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    debugImplementation("androidx.compose.ui:ui-tooling:$composeUiVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeUiVersion")
}

