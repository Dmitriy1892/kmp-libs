import io.github.dmitriy1892.kmp.libs.extensions.artifact.ios.iosRegularFramework
import io.github.dmitriy1892.kmp.libs.extensions.commonMainDependencies
import io.github.dmitriy1892.kmp.libs.extensions.commonTestDependencies
import io.github.dmitriy1892.kmp.libs.extensions.getApplicationVersionName
import io.github.dmitriy1892.kmp.libs.extensions.iosMainDependencies
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinxSerialization)
    id("kmp.compose.library")
    id("kmp.koin")
}

iosRegularFramework {
    baseName = "SharedSDK"
    transitiveExport = false
    isStatic = true

    export(projects.decompose.lifecycleCompose)
    export(projects.utils)
}

android {
    namespace = "io.github.dmitriy1892.kmp.libs.shared"
}

commonMainDependencies {
    implementation(projects.decompose.navigationKoinCompose)
    implementation(projects.mvi.core)
    implementation(projects.mvi.viewmodel)
    implementation(projects.mvvm.koin)
    implementation(projects.utils)

    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.decompose.compose.multiplatform)
    implementation(libs.decompose.core)
    implementation(libs.koin.core)
    implementation(libs.kotlinx.serialization.core)
}

commonTestDependencies {
    implementation(libs.kotlin.test)
}

iosMainDependencies {
    api(projects.decompose.lifecycleCompose)
    api(projects.utils)
}

compose.desktop {
    application {
        mainClass = "io.dmitriy1892.kmp.libs.shared.MainKt"

        nativeDistributions {
            targetFormats = setOf(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "SharedSDK"
            packageVersion = getApplicationVersionName()
        }
    }
}