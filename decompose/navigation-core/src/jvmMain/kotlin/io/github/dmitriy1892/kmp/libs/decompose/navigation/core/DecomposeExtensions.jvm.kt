package io.github.dmitriy1892.kmp.libs.decompose.navigation.core

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import javax.swing.SwingUtilities

val defaultJvmLifecycle = LifecycleRegistry()
val defaultJvmBackDispatcher = BackDispatcher()

val defaultJvmComponentContext = runOnUiThread {
    DefaultComponentContext(
        lifecycle = defaultJvmLifecycle,
        backHandler = defaultJvmBackDispatcher
    )
}

private fun <T> runOnUiThread(block: () -> T): T {
    if (SwingUtilities.isEventDispatchThread()) {
        return block()
    }

    var error: Throwable? = null
    var result: T? = null

    SwingUtilities.invokeAndWait {
        try {
            result = block()
        } catch (e: Throwable) {
            error = e
        }
    }

    error?.also { throw it }

    @Suppress("UNCHECKED_CAST")
    return result as T
}