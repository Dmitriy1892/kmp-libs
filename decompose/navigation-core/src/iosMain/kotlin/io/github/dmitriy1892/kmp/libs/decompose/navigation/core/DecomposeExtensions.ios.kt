package io.github.dmitriy1892.kmp.libs.decompose.navigation.core

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.backhandler.BackDispatcher
import io.github.dmitriy1892.kmp.libs.decompose.lifecycle.compose.IosLifecycleHandler

val defaultIosBackDispatcher = BackDispatcher()

val defaultIosComponentContext = DefaultComponentContext(
    lifecycle = IosLifecycleHandler.lifecycle,
    backHandler = defaultIosBackDispatcher
)