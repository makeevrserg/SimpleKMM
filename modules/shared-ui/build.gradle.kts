import org.jetbrains.compose.compose

plugins {
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
    kotlin("multiplatform")
    id("com.android.library")
}
kotlin {
    android() {
        apply(plugin = "kotlin-parcelize")
    }
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    listOf(iosX64("uikitX64"), iosArm64("uikitArm64")).forEach {
        it.binaries {
            executable {
                entryPoint = "main"
                freeCompilerArgs += listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics"
                )
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                implementation(compose.runtime)
                implementation(project(":modules:rick-morty"))
                implementation(project(":modules:localdb-dto"))
                implementation(project(":modules:localdb-api"))
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("io.ktor:ktor-client-core:${Dependencies.Domain.ktor}")
                // Navigation
                implementation("com.arkivanov.decompose:decompose:${Dependencies.Kotlin.decompose}")
//                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:${Dependencies.Kotlin.decompose}")
                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:${Dependencies.Kotlin.decompose}-native-compose")
                implementation(project(":modules:shared-logic"))

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:${Dependencies.Domain.ktor}")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:${Dependencies.Domain.ktor}")
            }
        }
        val androidTest by getting


        val uikitMain by creating {
            dependsOn(commonMain)
        }
        val uikitX64Main by getting {
            dependsOn(uikitMain)
        }
        val uikitArm64Main by getting {
            dependsOn(uikitMain)
        }
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
        implementation("com.google.android.exoplayer:exoplayer-core:2.18.1")
        implementation("com.google.android.exoplayer:exoplayer-dash:2.18.1")
        implementation("com.google.android.exoplayer:exoplayer-ui:2.18.1")
        implementation("com.google.android.exoplayer:exoplayer-hls:2.18.1")
        implementation("com.google.android.exoplayer:exoplayer-smoothstreaming:2.18.1")
//        implementation ("androidx.media3:media3-exoplayer:1.0.0-beta02")
//        implementation ("androidx.media3:media3-ui:1.0.0-beta02")
    }
}