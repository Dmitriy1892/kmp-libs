package com.github.dmitriy1892.kmp.libs.extensions

import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.gradle.kotlin.dsl.findByType

internal val Project.kotlinMultiplatformExtension: KotlinMultiplatformExtension
    get() = requireNotNull(extensions.findByType(KotlinMultiplatformExtension::class)) {
        "\"Project.kotlinMultiplatformExtension\" value may be called only from multiplatform module"
    }

fun Project.commonMainDependencies(block: KotlinDependencyHandler.() -> Unit) {
    with(kotlinMultiplatformExtension) {
        sourceSets.commonMain.dependencies(block)
    }
}

fun Project.androidMainDependencies(block: KotlinDependencyHandler.() -> Unit) {
    with(kotlinMultiplatformExtension) {
        sourceSets.androidMain.dependencies(block)
    }
}

fun Project.iosMainDependencies(block: KotlinDependencyHandler.() -> Unit) {
    with(kotlinMultiplatformExtension) {
        sourceSets.iosMain.dependencies(block)
    }
}

fun Project.commonTestDependencies(block: KotlinDependencyHandler.() -> Unit) {
    with(kotlinMultiplatformExtension) {
        sourceSets.commonTest.dependencies(block)
    }
}

@JvmName("implementationMinimalExternalModuleDependency")
internal fun DependencyHandlerScope.implementation(
    dependency: Provider<MinimalExternalModuleDependency>
) {
    add("implementation", dependency)
}

internal fun DependencyHandlerScope.implementation(
    dependency: ProjectDependency
) {
    add("implementation", dependency)
}

internal fun DependencyHandlerScope.implementation(
    dependency: Provider<ExternalModuleDependencyBundle>
) {
    add("implementation", dependency)
}


internal fun DependencyHandlerScope.kapt(
    dependency: Provider<MinimalExternalModuleDependency>
) {
    add("kapt", dependency)
}

internal fun DependencyHandlerScope.kaptAndroidTest(
    dependency: Provider<MinimalExternalModuleDependency>
) {
    add("kaptAndroidTest", dependency)
}