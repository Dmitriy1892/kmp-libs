import com.github.dmitriy1892.kmp.libs.extensions.commonMainDependencies
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
    librarySourceUrl = "https://github.com/Dmitriy1892/kmp-libs/mvvm.git",
    libraryGroupId = "com.github.dmitriy1892.kmplibs.mvvm",
    libraryVersion = libsCatalog.versions.kmp.libs.mvvm.get(),
    licenseUrl = "https://github.com/Dmitriy1892/kmp-libs/mvvm/LICENSE.md",
)

android {
    namespace = "com.github.dmitriy1892.kmplibs.mvvm.core"
}

commonMainDependencies {
    api(libs.kotlinx.coroutines.core)
    api(libs.androidx.lifecycle.viewmodel)
}