import io.github.dmitriy1892.kmp.libs.extensions.libs

plugins {
    id("multiplatform-config")
    id("org.jetbrains.compose")
    id("android-compose-config")
}

kotlin {
    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }

        commonMain.dependencies {
            implementation(compose.animation)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)

            implementation(libs.kotlinx.immutablecollections)
        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}