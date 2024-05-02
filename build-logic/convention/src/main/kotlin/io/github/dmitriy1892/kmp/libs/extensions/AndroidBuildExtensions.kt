package io.github.dmitriy1892.kmp.libs.extensions

import com.android.build.api.dsl.AndroidResources
import com.android.build.api.dsl.BuildFeatures
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.DefaultConfig
import com.android.build.api.dsl.Installation
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.ProductFlavor
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

/**
 * Function for android configuring, logically this function is similar as "android" block in gradle file.
 */
internal fun Project.androidOptions(
    block: CommonExtension<out BuildFeatures, out BuildType, out DefaultConfig, out ProductFlavor, out AndroidResources, out Installation>.() -> Unit
) {
    androidExtension.apply {
        block()
    }
}

/**
 * Function for configuring a base Kotlin with Android options
 */
internal fun CommonExtension<*, *, *, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

private val Project.androidExtension: CommonExtension<out BuildFeatures, out BuildType, out DefaultConfig, out ProductFlavor, out AndroidResources, out Installation>
    get() = extensions.findByType(BaseAppModuleExtension::class)
        ?: requireNotNull(extensions.findByType(LibraryExtension::class)) {
            "\"Project.androidExtension\" value may be called only from android application" +
                    " or android library gradle script"
        }