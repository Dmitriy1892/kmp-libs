import io.github.dmitriy1892.kmp.libs.extensions.commonMainDependencies
import io.github.dmitriy1892.kmp.libs.extensions.commonTestDependencies
import io.github.dmitriy1892.kmp.libs.extensions.publication.setMavenPublishConfig

plugins {
    id("kmp.library")
    id("publish-config")
}

setMavenPublishConfig(
    libraryName = "KMP-Utils",
    libraryDescription = "KMP-Utils is a library with useful classes and extensions for Kotlin Multiplatform development",
    librarySourceUrl = "https://github.com/Dmitriy1892/kmp-libs/tree/main/mvvm",
    libraryGroupId = "io.github.dmitriy1892.kmp.libs",
    libraryVersion = libs.versions.kmp.libs.utils.get(),
    licenseUrl = "https://github.com/Dmitriy1892/kmp-libs/blob/main/LICENSE.md",
)

android {
    namespace = "io.github.dmitriy1892.kmp.libs.utils"

    buildFeatures {
        buildConfig = true
    }
}

commonMainDependencies {
    implementation(libs.napier.logger)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.coroutines.core)
}

commonTestDependencies {
    implementation(libs.kotlin.test)
    implementation(libs.kotlin.test.common)
    implementation(libs.kotlin.test.annotations.common)
    implementation(libs.kotlin.test.coroutines.common)
}