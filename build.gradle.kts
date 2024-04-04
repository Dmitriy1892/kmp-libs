import com.github.dmitriy1892.kmp.libs.extensions.incrementVersionProperties

plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    id("build-logic-plugin").apply(false)
}

tasks.register("incrementSomeVersion") {
    incrementVersionProperties()
}