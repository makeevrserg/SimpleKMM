import org.jetbrains.compose.compose

plugins {
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Compose
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                // Ktor
                implementation("io.ktor:ktor-client-core:${Dependencies.Domain.ktor}")
                implementation("io.ktor:ktor-client-serialization:${Dependencies.Domain.ktor}")
                implementation("io.ktor:ktor-client-json:${Dependencies.Domain.ktor}")
                implementation("io.ktor:ktor-client-content-negotiation:${Dependencies.Domain.ktor}")
                implementation("io.ktor:ktor-serialization-kotlinx-json:${Dependencies.Domain.ktor}")
                // Serializtion
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Dependencies.Kotlin.serializationJson}")
                // Navigation
                implementation("com.arkivanov.decompose:decompose:${Dependencies.Kotlin.decompose}")
                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:${Dependencies.Kotlin.decompose}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val desktopMain by getting {
            dependencies {
                api(compose.preview)
                implementation("io.ktor:ktor-client-okhttp:${Dependencies.Domain.ktor}")
                implementation("io.ktor:ktor-client-core:${Dependencies.Domain.ktor}")
//                implementation("org.threeten:threetenbp:1.6.0")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                implementation(compose.runtime)
                implementation(compose.preview)
                implementation(compose.uiTooling)
                implementation("io.ktor:ktor-client-okhttp:${Dependencies.Domain.ktor}")
                implementation("io.ktor:ktor-client-core:${Dependencies.Domain.ktor}")
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:${Dependencies.Domain.ktor}")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}
android {
    namespace = "com.makeevrserg.simplekmm"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
}
dependencies {
    implementation("androidx.compose.ui:ui-graphics:1.2.1")
}


//dependencies {
//    add("kspCommonMainMetadata", "de.jensklingenberg.ktorfit:ktorfit-ksp:${Dependencies.Kotlin.ktorfit}")
//    add("kspAndroid", "de.jensklingenberg.ktorfit:ktorfit-ksp:${Dependencies.Kotlin.ktorfit}")
//    add("kspIosX64", "de.jensklingenberg.ktorfit:ktorfit-ksp:${Dependencies.Kotlin.ktorfit}")
//    add("kspIosSimulatorArm64", "de.jensklingenberg.ktorfit:ktorfit-ksp:${Dependencies.Kotlin.ktorfit}")
//    add("kspJvm", "de.jensklingenberg.ktorfit:ktorfit-ksp:${Dependencies.Kotlin.ktorfit}")
//
//}
