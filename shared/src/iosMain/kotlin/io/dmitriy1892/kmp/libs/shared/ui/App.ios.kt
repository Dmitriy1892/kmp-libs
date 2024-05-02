package io.dmitriy1892.kmp.libs.shared.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureIcon
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureOverlay
import io.dmitriy1892.kmp.libs.shared.navigation.RootNavGraph
import io.dmitriy1892.kmp.libs.shared.uikit.theme.SampleTheme
import io.github.dmitriy1892.kmp.libs.decompose.navigation.compose.ContentWithLifecycle
import io.github.dmitriy1892.kmp.libs.decompose.navigation.core.defaultIosBackDispatcher

@OptIn(ExperimentalDecomposeApi::class)
@Composable
actual fun App(defaultComponentContext: DefaultComponentContext) {

    val rootNavGraph = remember { RootNavGraph(defaultComponentContext) }

    SampleTheme {
        PredictiveBackGestureOverlay(
            modifier = Modifier.fillMaxSize(),
            backDispatcher = defaultIosBackDispatcher,
            backIcon = { progress, _ ->
                PredictiveBackGestureIcon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    progress = progress
                )
            }
        ) {
            rootNavGraph.ContentWithLifecycle()
        }
    }
}