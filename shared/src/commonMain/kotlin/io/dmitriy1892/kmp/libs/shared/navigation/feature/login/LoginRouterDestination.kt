package io.dmitriy1892.kmp.libs.shared.navigation.feature.login

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import io.dmitriy1892.kmp.libs.shared.ui.login.LoginRouter
import io.dmitriy1892.kmp.libs.shared.ui.login.LoginScreen
import io.github.dmitriy1892.kmp.libs.decompose.navigation.koin.compose.KoinComposeDestination

class LoginRouterDestination(
    componentContext: ComponentContext
) : KoinComposeDestination(componentContext), LoginRouter {

    @Composable
    override fun ContentScreen() {
        LoginScreen(
            viewModel = decomposeViewModel(),
            router = this
        )
    }
}