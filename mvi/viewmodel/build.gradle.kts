import io.github.dmitriy1892.kmp.libs.extensions.commonMainDependencies
import io.github.dmitriy1892.kmp.libs.extensions.publication.setMavenPublishConfig

plugins {
    id("kmp.library")
    id("publish-config")
}

setMavenPublishConfig(
    libraryName = "KMP-MVI",
    libraryDescription = "KMP-MVI is a library with useful classes and extensions for Kotlin Multiplatform development",
    librarySourceUrl = "https://github.com/Dmitriy1892/kmp-libs/tree/main/mvvm",
    libraryGroupId = "io.github.dmitriy1892.kmp.libs.mvi",
    libraryVersion = libs.versions.kmp.libs.mvi.get(),
    licenseUrl = "https://github.com/Dmitriy1892/kmp-libs/blob/main/LICENSE.md",
)

android {
    namespace = "io.github.dmitriy1892.kmp.libs.mvi.viewmodel"
}

commonMainDependencies {
    implementation(projects.mvi.core)
    implementation(projects.utils)

    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.kotlinx.coroutines.core)
}