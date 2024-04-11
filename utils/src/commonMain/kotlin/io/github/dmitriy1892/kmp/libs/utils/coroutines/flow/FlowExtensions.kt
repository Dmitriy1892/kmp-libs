package io.github.dmitriy1892.kmp.libs.utils.coroutines.flow

import io.github.dmitriy1892.kmp.libs.utils.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Extension function for converting a [StateFlow] to the [WrappedStateFlow]
 *
 * @return [WrappedStateFlow]
 */
fun <T: Any> StateFlow<T>.asWrappedStateFlow(): WrappedStateFlow<T> = WrappedStateFlow(this)

/**
 * Extension function for converting a [MutableStateFlow] to the [WrappedStateFlow]
 *
 * @return [WrappedStateFlow]
 */
fun <T: Any> MutableStateFlow<T>.asWrappedStateFlow(): WrappedStateFlow<T> =
    this.asStateFlow().asWrappedStateFlow()

/**
 * Extension function for converting a [SharedFlow] to the [WrappedSharedFlow]
 *
 * @return [WrappedSharedFlow]
 */
fun <T: Any> SharedFlow<T>.asWrappedSharedFlow(): WrappedSharedFlow<T> =
    WrappedSharedFlow(this)

/**
 * Extension function for converting a [MutableSharedFlow] to the [WrappedSharedFlow]
 *
 * @return [WrappedSharedFlow]
 */
fun <T: Any> MutableSharedFlow<T>.asWrappedSharedFlow(): WrappedSharedFlow<T> =
    this.asSharedFlow().asWrappedSharedFlow()

/**
 * Extension function for converting a [Flow] to the [WrappedFlow]
 *
 * @return [WrappedFlow]
 */
fun <T : Any> Flow<T>.asWrappedFlow(): WrappedFlow<T> = WrappedFlow(this)

/**
 * Extension function that subscribes to flow and returns a [Closeable]
 * for subscription cancelling possibility.
 *
 * @param block lambda that provides an emitted [T] items.
 * @return [Closeable] for possibility to unsubscribe.
 */
@RestrictedFlowSubscription
internal fun <T> Flow<T>.subscribeToFlow(block: (T) -> Unit): Closeable {
    val context = CoroutineScope(Dispatchers.Main + SupervisorJob())

    this.onEach(block).launchIn(context)

    return object : Closeable {
        override fun close(): Unit = context.cancel()
    }
}