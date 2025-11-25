plugins {
    alias(libs.plugins.androidApplication)apply false
    alias(libs.plugins.kotlinAndroid)apply false
    alias(libs.plugins.compose.compiler) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${libs.versions.agp.get()}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.get()}")
        classpath(libs.hilt.android.gradle.plugin)
    }
}
