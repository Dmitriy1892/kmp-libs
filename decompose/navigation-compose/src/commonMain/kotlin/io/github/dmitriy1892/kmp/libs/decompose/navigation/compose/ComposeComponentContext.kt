package io.github.dmitriy1892.kmp.libs.decompose.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.arkivanov.decompose.ComponentContext
import io.github.dmitriy1892.kmp.libs.decompose.lifecycle.compose.LocalLifecycleOwner

/**
 * [ComponentContext] with composable UI screen.
 */
interface ComposeComponentContext : ComponentContext {

    /**
     * Function that holds a composable screen.
     */
    @Composable
    fun ContentScreen()
}

/**
 * Extension function that wraps a [ComposeComponentContext.ContentScreen] and
 * provides a [LocalLifecycleOwner] for it.
 *
 */
@Composable
fun ComposeComponentContext.ContentWithLifecycle() {
    CompositionLocalProvider(LocalLifecycleOwner provides this) {
        ContentScreen()
    }
}