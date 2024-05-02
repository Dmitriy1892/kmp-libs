package io.github.dmitriy1892.kmp.libs.decompose.navigation.koin.compose

import com.arkivanov.decompose.ComponentContext
import kotlinx.serialization.Serializable

abstract class SimpleKoinComposeNavGraph<Config: @Serializable Any>(
    componentContext: ComponentContext
) : KoinComposeNavGraph<Config, KoinComposeDestination>(componentContext)