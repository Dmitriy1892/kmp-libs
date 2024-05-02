package io.dmitriy1892.kmp.libs.shared

import androidx.compose.ui.window.ComposeUIViewController
import io.dmitriy1892.kmp.libs.shared.ui.App
import io.github.dmitriy1892.kmp.libs.decompose.navigation.core.defaultIosComponentContext
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController {
    App(defaultIosComponentContext)
}