import org.jetbrains.compose.compose

plugins {
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
    kotlin("multiplatform")
//    kotlin("native.cocoapods")
    id("com.android.library")
}
val qdsfdhvh = "1.1.8"
kotlin {
    android(){
        apply(plugin="kotlin-parcelize")
    }
//    iosX64()
//    iosArm64()
//    iosSimulatorArm64()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
//    cocoapods {
//        summary = "Some description for the Shared Module"
//        homepage = "Link to the Shared Module homepage"
//        version = "1.0"
//        ios.deploymentTarget = "14.1"
//        podfile = project.file("../iosApp/Podfile")
//        framework {
//            baseName = "shared"
//        }
//    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                implementation(compose.runtime)
                implementation(compose.preview)
                implementation(compose.uiTooling)
                implementation(compose("org.jetbrains.compose.ui:ui-tooling"))
//                api("io.github.qdsfdhvh:image-loader:$qdsfdhvh")
                implementation(project(":shared"))

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
//        val desktopMain by getting {
//            dependencies {
//                dependsOn(commonMain)
//            }
//        }
        val desktopMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:${Dependencies.Domain.ktor}")
                implementation("io.ktor:ktor-client-core:${Dependencies.Domain.ktor}")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:${Dependencies.Domain.ktor}")
                implementation("io.ktor:ktor-client-core:${Dependencies.Domain.ktor}")
                implementation("io.coil-kt:coil-compose:2.2.2")

                implementation ("com.arkivanov.decompose:decompose:${Dependencies.Kotlin.decompose}")
                implementation ("com.arkivanov.decompose:extensions-compose-jetbrains:${Dependencies.Kotlin.decompose}")
            }
        }
        val androidTest by getting
//        val iosX64Main by getting
//        val iosArm64Main by getting
//        val iosSimulatorArm64Main by getting
//        val iosMain by creating {
//            dependsOn(commonMain)
//            iosX64Main.dependsOn(this)
//            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
//            dependencies {
//            }
//        }
//        val iosX64Test by getting
//        val iosArm64Test by getting
//        val iosSimulatorArm64Test by getting
//        val iosTest by creating {
//            dependsOn(commonTest)
//            iosX64Test.dependsOn(this)
//            iosArm64Test.dependsOn(this)
//            iosSimulatorArm64Test.dependsOn(this)
//        }
    }
}
android {
    namespace = "com.makeevrserg.simplekmm.ui"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
    dependencies {
        implementation("io.coil-kt:coil-compose:2.2.2")

        implementation ("com.arkivanov.decompose:decompose:${Dependencies.Kotlin.decompose}")
        implementation ("com.arkivanov.decompose:extensions-compose-jetbrains:${Dependencies.Kotlin.decompose}")
    }
}
dependencies {
    implementation("androidx.compose.ui:ui:1.2.1")
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation ("com.arkivanov.decompose:decompose:${Dependencies.Kotlin.decompose}")
    implementation ("com.arkivanov.decompose:extensions-compose-jetbrains:${Dependencies.Kotlin.decompose}")
    //    implementation("androidx.compose.ui:ui-tooling-preview:1.0.0-rc01")
//    debugImplementation("androidx.compose.ui:ui-tooling:1.0.0-rc01")
}
