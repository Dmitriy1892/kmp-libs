package io.dmitriy1892.kmp.libs.shared.ui.login

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import io.dmitriy1892.kmp.libs.shared.uikit.theme.SampleTheme
import io.github.dmitriy1892.kmp.libs.decompose.lifecycle.compose.collectAsStateWithLifecycle
import io.github.dmitriy1892.kmp.libs.mvi.core.extensions.stateFlow
import kmp_libs.shared.generated.resources.Res

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    router: LoginRouter
) {
    Res
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    LoginScreenContent(state)
}

@Composable
private fun LoginScreenContent(
    state: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Cyan)
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(color = Color.Gray),
            color = Color.Magenta,
            text = "Header"
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(color = Color.DarkGray),
            color = Color.Magenta,
            text = "Under header text"
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.Red,
            text = "Login screen, random value: $state"
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(color = Color.Gray),
            color = Color.Magenta,
            text = "Bottom text"
        )
    }
}

@Preview
@Composable
private fun LoginScreenContentPreview() {
    SampleTheme { LoginScreenContent(275) }
}