import com.github.dmitriy1892.kmp.libs.extensions.commonMainDependencies
import com.github.dmitriy1892.kmp.libs.extensions.getCommandLineProperty
import com.github.dmitriy1892.kmp.libs.extensions.getSecretProperties
import com.github.dmitriy1892.kmp.libs.extensions.libsCatalog
import com.github.dmitriy1892.kmp.libs.extensions.publication.setMavenPublishConfig

plugins {
    id("android-library-config")
    id("multiplatform-config")

    id("publish-config")
}

setMavenPublishConfig(
    libraryName = "KMP-MVVM",
    libraryDescription = "KMP-MVVM is a library with useful classes and extensions for Kotlin Multiplatform development",
    librarySourceUrl = "https://github.com/Dmitriy1892/kmp-libs/tree/main/mvvm",
    libraryGroupId = "com.github.dmitriy1892.kmplibs.mvvm",
    libraryVersion = libsCatalog.versions.kmp.libs.mvvm.get(),
    licenseUrl = "https://github.com/Dmitriy1892/kmp-libs/blob/main/LICENSE.md",
)

android {
    namespace = "com.github.dmitriy1892.kmplibs.mvvm.koin"
}

commonMainDependencies {
    implementation(project(":core"))
    implementation(libs.koin.core)
}