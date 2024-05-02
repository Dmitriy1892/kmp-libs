import io.github.dmitriy1892.kmp.libs.extensions.getApplicationVersionCode
import io.github.dmitriy1892.kmp.libs.extensions.getApplicationVersionName

plugins {
    id("android-application-config")
    id("android-compose-config")
}

android {
    namespace = "com.github.dmitriy1892.kmp.libs.android"

    defaultConfig {
        applicationId = "io.github.dmitriy1892.kmp.libs.android"
        versionCode = getApplicationVersionCode()
        versionName = getApplicationVersionName()
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(projects.shared)
    implementation(projects.utils)

    implementation(libs.androidx.activity.compose)
    implementation(libs.decompose.android)
    implementation(libs.decompose.core)
    implementation(libs.koin.android)
}