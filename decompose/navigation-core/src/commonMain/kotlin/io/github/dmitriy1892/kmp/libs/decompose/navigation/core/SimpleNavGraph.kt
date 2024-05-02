package io.github.dmitriy1892.kmp.libs.decompose.navigation.core

import com.arkivanov.decompose.ComponentContext

/**
 * Simple nav graph
 *
 * @param Config
 * @constructor
 *
 * @param componentContext
 */
abstract class SimpleNavGraph<Config: Any>(
    componentContext: ComponentContext
) : NavGraph<Config, Destination>(componentContext)