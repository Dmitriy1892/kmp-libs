package io.dmitriy1892.kmp.libs.shared.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.arkivanov.decompose.DefaultComponentContext
import io.dmitriy1892.kmp.libs.shared.navigation.RootNavGraph
import io.dmitriy1892.kmp.libs.shared.uikit.theme.SampleTheme
import io.github.dmitriy1892.kmp.libs.decompose.navigation.compose.ContentWithLifecycle

@Composable
actual fun App(defaultComponentContext: DefaultComponentContext) {
    val rootNavGraph = remember { RootNavGraph(defaultComponentContext) }

    SampleTheme {
        rootNavGraph.ContentWithLifecycle()
    }
}