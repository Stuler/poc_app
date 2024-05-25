plugins {
    //trick: for the same plugin versions in all sub-modules
    kotlin("multiplatform").version("1.8.10").apply(false)
    id("com.android.application").version("8.1.1").apply(false)
    id("com.android.library").version("8.1.1").apply(false)
    id("org.jetbrains.compose").apply(false)
    kotlin("plugin.serialization") version "1.9.0"
}

buildscript {
    dependencies {
        classpath("dev.icerock.moko:resources-generator:0.23.0")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}