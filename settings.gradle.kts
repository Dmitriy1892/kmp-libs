enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "KMP-Libs"

includeBuild("build-logic")
includeBuild("mvvm")

include(":androidApp")
include(":shared")

// Workaround for "gradle.properties", "local.properties" and gradle wrapper files sharing,
// issue - https://github.com/gradle/gradle/issues/2534
val compositeBuilds = listOf(
    "mvvm"
)
val gradleProperties = "gradle.properties"
val localProperties = "local.properties"
val wrapperJar = "gradle-wrapper.jar"
val wrapperProperties = "gradle-wrapper.properties"
val wrapperFile = "gradlew"
val wrapperBat = "gradlew.bat"

compositeBuilds.forEach { moduleName ->
    val gradlePropsFile = File(rootDir, "$moduleName/$gradleProperties").apply {
        if (exists()) delete()
    }
    file(gradleProperties).copyTo(gradlePropsFile)

    val localPropsFile = File(rootDir, "$moduleName/$localProperties").apply {
        if (exists()) delete()
    }
    file(localProperties).copyTo(localPropsFile)

    val gradleWrapperJar = File(rootDir, "$moduleName/gradle/wrapper/$wrapperJar").apply {
        if (exists()) delete()
    }
    file("gradle/wrapper/$wrapperJar").copyTo(gradleWrapperJar)

    val gradleWrapperProperties = File(rootDir, "$moduleName/gradle/wrapper/$wrapperProperties").apply {
        if (exists()) delete()
    }
    file("gradle/wrapper/$wrapperProperties").copyTo(gradleWrapperProperties)

    val gradleWrapperFile = File(rootDir, "$moduleName/$wrapperFile").apply {
        if (exists()) delete()
    }
    file(wrapperFile).copyTo(gradleWrapperFile)

    val gradleWrapperBat = File(rootDir, "$moduleName/$wrapperBat").apply {
        if (exists()) delete()
    }
    file(wrapperBat).copyTo(gradleWrapperBat)
}