package io.github.dmitriy1892.kmp.libs.decompose.navigation.compose

import com.arkivanov.decompose.ComponentContext
import kotlinx.serialization.Serializable

abstract class SimpleComposeNavGraph<Config: @Serializable Any>(
    componentContext: ComponentContext
) : ComposeNavGraph<Config, ComposeDestination>(componentContext)