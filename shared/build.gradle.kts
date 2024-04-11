import io.github.dmitriy1892.kmp.libs.extensions.artifact.ios.spm.swiftPackageManagerFramework
import io.github.dmitriy1892.kmp.libs.extensions.commonTestDependencies

plugins {
    id("android-library-config")
    id("multiplatform-config")
}

swiftPackageManagerFramework(
    archiveName = "shared",
    bundleId = "io.github.dmitriy1892.kmp.libs.shared"
)

commonTestDependencies {
    implementation(libs.kotlin.test)
}

android {
    namespace = "io.github.dmitriy1892.kmp.libs"
}