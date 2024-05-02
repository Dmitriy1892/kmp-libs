import io.github.dmitriy1892.kmp.libs.extensions.commonMainDependencies
import io.github.dmitriy1892.kmp.libs.extensions.publication.setMavenPublishConfig

plugins {
    alias(libs.plugins.kotlinxSerialization)
    id("kmp.compose.library")
    id("publish-config")
}

setMavenPublishConfig(
    libraryName = "KMP-MVVM",
    libraryDescription = "KMP-MVVM is a library with useful classes and extensions for Kotlin Multiplatform development",
    librarySourceUrl = "https://github.com/Dmitriy1892/kmp-libs/tree/main/mvvm",
    libraryGroupId = "io.github.dmitriy1892.kmp.libs.decompose",
    libraryVersion = libs.versions.kmp.libs.decompose.get(),
    licenseUrl = "https://github.com/Dmitriy1892/kmp-libs/blob/main/LICENSE.md",
)

android {
    namespace = "io.github.dmitriy1892.kmp.libs.decompose.navigation.compose"
}

commonMainDependencies {
    api(projects.decompose.navigationCore)
    api(projects.decompose.lifecycleCompose)

    implementation(projects.mvvm.koin)
    implementation(projects.utils)

    implementation(libs.kotlinx.serialization.core)
    implementation(libs.decompose.core)
    implementation(libs.decompose.compose.multiplatform)
    implementation(libs.androidx.lifecycle.viewmodel)
}

