plugins {
    kotlin("plugin.serialization")
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
    js(IR) {
        browser()
        binaries.executable()
    }
    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../../iosApp/Podfile")
        framework {
            baseName = "localdb_api"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Ktor
                implementation("io.ktor:ktor-client-core:${Dependencies.Domain.ktor}")
                implementation("io.ktor:ktor-client-serialization:${Dependencies.Domain.ktor}")
                implementation("io.ktor:ktor-client-json:${Dependencies.Domain.ktor}")
                implementation("io.ktor:ktor-client-content-negotiation:${Dependencies.Domain.ktor}")
                implementation("io.ktor:ktor-serialization-kotlinx-json:${Dependencies.Domain.ktor}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Dependencies.Kotlin.serializationJson}")
                implementation(project(":modules:localdb-dto"))
            }
        }
        val commonTest by getting
        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:${Dependencies.Domain.ktor}")
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
    namespace = "com.makeevrserg.simplekmm.localdb_api"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
}