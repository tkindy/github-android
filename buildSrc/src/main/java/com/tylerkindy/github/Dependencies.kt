package com.tylerkindy.github

object Versions {
    val compileSdk = 28
    val minSdk = 19
    val targetSdk = 28

    val androidGradle = "3.3.0-alpha05"
    val kotlin = "1.2.60"
    val appCompat = "1.0.0-rc01"
    val constraintLayout = "1.1.2"
    val junit = "4.12"
    val testRunner = "1.1.0-alpha4"
    val espresso = "3.1.0-alpha4"
    val navigation = "1.0.0-alpha05"
    val recyclerView = "1.0.0-alpha1"
}

object Deps {
    val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"

    val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val testRunner = "androidx.test:runner:${Versions.testRunner}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val navigationFragment = "android.arch.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationUi = "android.arch.navigation:navigation-ui-ktx:${Versions.navigation}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"

    val junit = "junit:junit:${Versions.junit}"
}
