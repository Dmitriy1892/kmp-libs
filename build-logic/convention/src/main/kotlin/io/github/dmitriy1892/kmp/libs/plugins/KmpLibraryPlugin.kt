package io.github.dmitriy1892.kmp.libs.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class KmpLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("android-library-config")
                apply("multiplatform-config")
            }
        }
    }
}