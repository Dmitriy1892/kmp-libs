package io.github.dmitriy1892.kmp.libs.plugins

import io.github.dmitriy1892.kmp.libs.extensions.androidMainDependencies
import io.github.dmitriy1892.kmp.libs.extensions.commonMainDependencies
import io.github.dmitriy1892.kmp.libs.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

class KoinPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugins.googleKsp.get().pluginId)
            }

            commonMainDependencies {
                implementation(libs.koin.core)
                implementation(libs.koin.annotations)
            }

            androidMainDependencies {
                implementation(libs.koin.android)
            }

            dependencies {
                add("kspCommonMainMetadata", libs.koin.ksp.compiler.get())
            }

            // WORKAROUND FOR KOIN KSP: ADD this dependsOn("kspCommonMainKotlinMetadata") instead of above dependencies
            tasks.withType<KotlinCompile<*>>().configureEach {
                if (name != "kspCommonMainKotlinMetadata") {
                    dependsOn("kspCommonMainKotlinMetadata")
                }
            }
        }
    }
}