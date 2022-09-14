import org.jetbrains.compose.compose

plugins {
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
    kotlin("multiplatform")
//    kotlin("native.cocoapods")
    id("com.android.library")
}
repositories {
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://jitpack.io")
}
val ktorVersion = "2.1.1"
val serialization_version = "1.2.1"
val koil = "2.2.1"
val qdsfdhvh = "1.1.8"
kotlin {
    android()
//    iosX64()
//    iosArm64()
//    iosSimulatorArm64()
    jvm() {
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
                api("io.github.qdsfdhvh:image-loader:$qdsfdhvh")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation(project(":domain"))
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
        val jvmMain by getting {
            dependencies {
                dependsOn(commonMain)
            }
        }
        val androidMain by getting{
            dependencies {
                dependsOn(commonMain)
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
}
dependencies {
//    implementation("androidx.compose.ui:ui-tooling-preview:1.0.0-rc01")
//    debugImplementation("androidx.compose.ui:ui-tooling:1.0.0-rc01")
}
