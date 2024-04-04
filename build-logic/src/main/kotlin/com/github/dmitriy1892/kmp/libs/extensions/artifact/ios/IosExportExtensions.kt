package com.github.dmitriy1892.kmp.libs.extensions.artifact.ios

import com.github.dmitriy1892.kmp.libs.extensions.kotlinMultiplatformExtension
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework

/**
 * Ios regular framework configuration extension.
 *
 * @param configuration block with [Framework] receiver. Configure your exports inside that lambda.
 */
fun Project.iosRegularFramework(
    configuration: Framework.() -> Unit
) {
    with(kotlinMultiplatformExtension) {
        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        ).forEach {
            it.binaries.framework {
                configuration()
            }
        }
    }
}