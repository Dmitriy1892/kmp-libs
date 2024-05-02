package io.github.dmitriy1892.kmp.libs.decompose.navigation.koin.compose

import androidx.lifecycle.ViewModelProvider
import com.arkivanov.decompose.ComponentContext
import io.github.dmitriy1892.kmp.libs.decompose.navigation.compose.ComposeNavGraph
import kotlinx.serialization.Serializable
import org.koin.core.component.inject

abstract class KoinComposeNavGraph<Config: @Serializable Any, Destination: Any>(
    componentContext: ComponentContext
) : ComposeNavGraph<Config, Destination>(componentContext), KoinComposeComponentContext {

    override val viewModelFactory: Lazy<ViewModelProvider.Factory> = inject()
}