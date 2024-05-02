package io.github.dmitriy1892.kmp.libs.decompose.navigation.compose

import com.arkivanov.decompose.ComponentContext
import io.github.dmitriy1892.kmp.libs.decompose.navigation.core.NavGraph
import kotlinx.serialization.Serializable

/**
 * Stack navigation graph class. Holds and manages a navigation stack.
 *
 * @param Config configuration type of navigation stack. Must be [Serializable].
 * This type can contain a navigation arguments.
 * @param Destination type that describes or wraps a [io.github.dmitriy1892.kmp.libs.navigation.core.Destination].
 * @param componentContext [ComponentContext]
 */
abstract class ComposeNavGraph<Config: @Serializable Any, Destination: Any>(
    componentContext: ComponentContext
) : NavGraph<Config, Destination>(componentContext), ComposeComponentContext