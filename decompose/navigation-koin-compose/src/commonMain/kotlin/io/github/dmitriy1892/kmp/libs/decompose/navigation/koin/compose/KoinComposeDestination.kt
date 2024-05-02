package io.github.dmitriy1892.kmp.libs.decompose.navigation.koin.compose

import androidx.lifecycle.ViewModelProvider
import com.arkivanov.decompose.ComponentContext
import io.github.dmitriy1892.kmp.libs.decompose.navigation.compose.ComposeDestination
import org.koin.core.component.inject

abstract class KoinComposeDestination(
    componentContext: ComponentContext
) : ComposeDestination(componentContext), KoinComposeComponentContext {

    override val viewModelFactory: Lazy<ViewModelProvider.Factory> = inject()
}