import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}
kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
//        val commonMain by getting {
//            dependencies {
//                implementation(compose.ui)
//                implementation(compose.foundation)
//                implementation(compose.material)
//                implementation(compose.runtime)
//                implementation(":modules:shared-logic")
//            }
//        }
        val jsMain by getting {
            dependencies {
                implementation(npm("highlight.js", "10.7.2"))
                implementation(compose.web.core)
                implementation(project(":modules:rick-morty"))
                implementation(project(":modules:shared-logic"))
            }
        }
    }
}

compose.experimental {
    web.application {}
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
