package io.github.dmitriy1892.kmp.libs.decompose.navigation.compose

import com.arkivanov.decompose.ComponentContext
import io.github.dmitriy1892.kmp.libs.decompose.navigation.core.Destination

abstract class ComposeDestination(
    componentContext: ComponentContext
) : Destination(componentContext), ComposeComponentContext