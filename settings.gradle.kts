import java.net.URI

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")
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
        maven {
            url = URI("https://androidx.dev/storage/compose-compiler/repository/")
        }
        mavenLocal()
    }
}

rootProject.name = "KMP-Libs"

include(":androidApp")
include(":shared")

include(
    ":mvi:core",
    ":mvi:viewmodel",

    ":mvvm:core",
    ":mvvm:koin",

    ":utils",

    ":decompose:lifecycle-compose",
    ":decompose:navigation-core",
    ":decompose:navigation-compose",
    ":decompose:navigation-koin-compose",
)