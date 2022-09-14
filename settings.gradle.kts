pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
        google()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://kotlin.bintray.com/kotlinx/")
        maven("https://plugins.gradle.org/m2/")
        maven("https://jitpack.io")
    }

}

dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
        google()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://kotlin.bintray.com/kotlinx/")
        maven("https://plugins.gradle.org/m2/")
        maven("https://jitpack.io")
    }
}

rootProject.name = "SimpleKMM"
include(":androidApp")
include(":desktop")
include(":shared")
include(":domain")
include(":shared-ui")
