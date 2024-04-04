import com.github.dmitriy1892.kmp.libs.extensions.artifact.ios.spm.swiftPackageManagerFramework
import com.github.dmitriy1892.kmp.libs.extensions.commonTestDependencies

plugins {
    id("android-library-config")
    id("multiplatform-config")
}

swiftPackageManagerFramework(
    archiveName = "shared",
    bundleId = "com.github.dmitriy1892.kmp.libs.shared"
)

commonTestDependencies {
    implementation(libs.kotlin.test)
}

android {
    namespace = "com.github.dmitriy1892.kmp.libs"
}