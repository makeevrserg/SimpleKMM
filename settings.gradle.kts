pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://plugins.gradle.org/m2/")
    }

}

dependencyResolutionManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://plugins.gradle.org/m2/")
    }
}

rootProject.name = "SimpleKMM"
include(":androidApp")
include(":desktop")
include(":js")
include(":KiosApp")
include(":modules:localdb-dto")
include(":modules:rick-morty")
include(":modules:localdb-api")
include(":modules:shared-ui")
include(":modules:shared-logic")
