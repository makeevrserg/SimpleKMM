import org.jetbrains.compose.experimental.dsl.IOSDevices
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

version = Dependencies.version

repositories {
    mavenCentral()
    mavenLocal()
    google()
}

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
                implementation(compose.materialIconsExtended)
                implementation(compose.runtime)
                implementation ("com.arkivanov.decompose:decompose:${Dependencies.Kotlin.decompose}")
                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:${Dependencies.Kotlin.decompose}-native-compose")

                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("io.ktor:ktor-client-core:${Dependencies.Domain.ktor}")

                implementation(project(":modules:rick-morty"))
                implementation(project(":modules:localdb-dto"))
                implementation(project(":modules:localdb-api"))
                implementation(project(":modules:shared-ui"))
            }
        }
        val uikitX64Main by getting {
            dependsOn(uikitMain)
        }
        val uikitArm64Main by getting {
            dependsOn(uikitMain)
        }
    }
}

compose.experimental {
    uikit.application {
        bundleIdPrefix = "com.makeevrserg.simplekmm"
        projectName = "SimpleKMM"
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