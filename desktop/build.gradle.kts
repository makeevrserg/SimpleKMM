import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}
kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                // Compose
                implementation(project(":shared-ui"))
                implementation(compose.desktop.currentOs)
                // Navigation
                implementation ("com.arkivanov.decompose:decompose:${Dependencies.Kotlin.decompose}")
                implementation ("com.arkivanov.decompose:extensions-compose-jetbrains:${Dependencies.Kotlin.decompose}")
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.makeevrserg.simplekmm.desktop.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "jvm"
            packageVersion = "1.0.0"
        }
    }
}
