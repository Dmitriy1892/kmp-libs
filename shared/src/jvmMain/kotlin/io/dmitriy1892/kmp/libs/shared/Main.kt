package io.dmitriy1892.kmp.libs.shared

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import io.dmitriy1892.kmp.libs.shared.di.KoinDiHolder
import io.dmitriy1892.kmp.libs.shared.ui.App
import io.github.dmitriy1892.kmp.libs.decompose.navigation.core.defaultJvmComponentContext
import io.github.dmitriy1892.kmp.libs.decompose.navigation.core.defaultJvmLifecycle
import io.github.dmitriy1892.kmp.libs.utils.platform.PlatformConfiguration

@OptIn(ExperimentalDecomposeApi::class)
fun main() {
    KoinDiHolder.getInstance(PlatformConfiguration())
    application {
        val windowState = rememberWindowState()

        LifecycleController(defaultJvmLifecycle, windowState)

        Window(
            onCloseRequest = { exitApplication() },
            state = windowState,
            title = "Shared"
        ) {
            App(defaultJvmComponentContext)
        }
    }
}