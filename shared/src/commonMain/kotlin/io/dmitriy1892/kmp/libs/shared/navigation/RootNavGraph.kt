package io.dmitriy1892.kmp.libs.shared.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import io.dmitriy1892.kmp.libs.shared.navigation.feature.login.LoginRouterDestination
import io.github.dmitriy1892.kmp.libs.decompose.navigation.compose.ContentWithLifecycle
import io.github.dmitriy1892.kmp.libs.decompose.navigation.koin.compose.KoinComposeDestination
import io.github.dmitriy1892.kmp.libs.decompose.navigation.koin.compose.SimpleKoinComposeNavGraph
import kotlinx.serialization.KSerializer

class RootNavGraph(
    componentContext: DefaultComponentContext
) : SimpleKoinComposeNavGraph<RootConfig>(componentContext) {

    override val configSerializer: KSerializer<RootConfig> = RootConfig.serializer()
    override val initialConfig: RootConfig = RootConfig.Login

    @Composable
    override fun ContentScreen() {
        Children(
            stack = childStack,
            modifier = Modifier.fillMaxSize(),
            animation = stackAnimation(slide())
        ) {
           it.instance.ContentWithLifecycle()
        }
    }


    override fun createDestination(
        config: RootConfig,
        componentContext: ComponentContext
    ): KoinComposeDestination = when (config) {
        is RootConfig.Login -> LoginRouterDestination(componentContext)
    }
}