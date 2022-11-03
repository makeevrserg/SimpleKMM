import org.jetbrains.compose.experimental.dsl.IOSDevices
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

version = "1.0.0"

kotlin {
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
        val uikitMain by creating {
            dependencies {
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)
                implementation ("com.arkivanov.decompose:decompose:${Dependencies.Kotlin.decompose}")
            }
        }
        val uikitX64Main by getting {
            dependsOn(uikitMain)
            dependencies {
            }
        }
        val uikitArm64Main by getting {
            dependsOn(uikitMain)
            dependencies {
            }
        }
    }
}

compose.experimental {
    uikit.application {
        bundleIdPrefix = "com.makeevrserg"
        projectName = "SlackComposeClone"
        deployConfigurations {
            simulator("IPhone13Pro") {
                // Usage: ./gradlew iosDeployIPhone8Debug
                device = IOSDevices.IPHONE_13_PRO
            }
            simulator("IPad") {
                // Usage: ./gradlew iosDeployIPadDebug
                device = IOSDevices.IPAD_MINI_6th_Gen
            }
            connectedDevice("Device") {
                // Usage: ./gradlew iosDeployDeviceRelease
                this.teamId = "***"
            }
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

kotlin {
    targets.withType<KotlinNativeTarget> {
        binaries.all {
            // TODO: the current compose binary surprises LLVM, so disable checks for now.
            freeCompilerArgs += "-Xdisable-phases=VerifyBitcode"
        }
    }
}