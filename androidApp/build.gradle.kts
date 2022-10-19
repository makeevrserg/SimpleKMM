plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.compose")
    id("kotlin-parcelize")
}

android {
    namespace = "com.makeevrserg.simplekmm.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.makeevrserg.simplekmm.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.1"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material)

    implementation(project(":shared-ui"))

    implementation("androidx.activity:activity-compose:1.6.0")

    implementation("io.coil-kt:coil-compose:2.2.2")

    implementation ("com.arkivanov.decompose:decompose:${Dependencies.Kotlin.decompose}")
    implementation ("com.arkivanov.decompose:extensions-compose-jetbrains:${Dependencies.Kotlin.decompose}")
    implementation("androidx.compose.material:material:1.2.1")

}