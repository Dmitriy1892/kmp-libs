package io.github.dmitriy1892.kmp.libs.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class KmpComposeLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("android-library-config")
                apply("android-compose-config")
                apply("multiplatform-compose-config")
            }
        }
    }
}