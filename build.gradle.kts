plugins {
    //trick: for the same plugin versions in all submodules
    id("com.android.application").version("7.2.0").apply(false) // 7.4.0-alpha10
    id("com.android.library").version("7.2.0").apply(false) // 7.4.0-alpha10
    kotlin("android").version("1.7.10").apply(false)
    kotlin("multiplatform").version("1.7.10").apply(false)
    kotlin("plugin.serialization").version("1.7.10").apply(false)
    id("org.jetbrains.compose").version("1.2.0-alpha01-dev774").apply(false)
    id("com.google.devtools.ksp").version("1.7.10-1.0.6").apply(false)
}
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
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
