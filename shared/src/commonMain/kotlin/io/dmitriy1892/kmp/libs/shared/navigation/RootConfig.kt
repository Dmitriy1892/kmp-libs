package io.dmitriy1892.kmp.libs.shared.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface RootConfig {

    @Serializable
    data object Login : RootConfig
}