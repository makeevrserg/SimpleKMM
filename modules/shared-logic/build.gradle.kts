import org.jetbrains.compose.compose

plugins {
    kotlin("plugin.serialization")
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("native.cocoapods")
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
                implementation(project(":modules:rick-morty"))
                implementation(project(":modules:localdb-dto"))
                implementation(project(":modules:localdb-api"))
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("io.ktor:ktor-client-core:${Dependencies.Domain.ktor}")
                implementation("io.ktor:ktor-client-core:${Dependencies.Domain.ktor}")
                implementation("io.ktor:ktor-client-serialization:${Dependencies.Domain.ktor}")
                implementation("io.ktor:ktor-client-json:${Dependencies.Domain.ktor}")
                implementation("io.ktor:ktor-client-content-negotiation:${Dependencies.Domain.ktor}")
                implementation("io.ktor:ktor-serialization-kotlinx-json:${Dependencies.Domain.ktor}")
                implementation("com.arkivanov.decompose:decompose:${Dependencies.Kotlin.decompose}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val desktopMain by getting
        val androidMain by getting
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
    namespace = "com.makeevrserg.simplekmm.shared_logic"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
}