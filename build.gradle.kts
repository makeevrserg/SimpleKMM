plugins {
    //trick: for the same plugin versions in all submodules
    id("com.android.application").version(Dependencies.Android.application).apply(false) // 7.4.0-alpha10
    id("com.android.library").version(Dependencies.Android.application).apply(false) // 7.4.0-alpha10
    kotlin("android").version(Dependencies.Kotlin.version).apply(false)
    kotlin("multiplatform").version(Dependencies.Kotlin.version).apply(false)
    kotlin("plugin.serialization").version(Dependencies.Kotlin.version).apply(false)
    id("org.jetbrains.compose").version(Dependencies.Compose.jetbrainsCompose).apply(false)
}
repositories {
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://jitpack.io")
}
allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://jitpack.io")
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
