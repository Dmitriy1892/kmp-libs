import io.github.dmitriy1892.kmp.libs.extensions.getApplicationVersionCode
import io.github.dmitriy1892.kmp.libs.extensions.getApplicationVersionName

plugins {
    id("com.android.application")
    kotlin("android")
    id("android-base-config")
}

android {
    defaultConfig {
        versionCode = getApplicationVersionCode()
        versionName = getApplicationVersionName()
    }

    packaging {
        resources {
            excludes += listOf(
                "META-INF/{AL2.0,LGPL2.1}",
                "META-INF/DEPENDENCIES",
                "META-INF/DEPENDENCIES.txt",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/LICENSE-FIREBASE.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/*.kotlin_module",
                "META-INF/versions/9/previous-compilation-data.bin"
            )
        }
    }
}