package io.github.dmitriy1892.kmp.libs.utils.coroutines.flow

import io.github.dmitriy1892.kmp.libs.utils.Closeable
import kotlinx.coroutines.flow.StateFlow

/**
 * Wrapper over [StateFlow] for possibility to using flow from iOS without pain.
 *
 * @property origin origin kotlin [StateFlow] that need to wrap
 */
class WrappedStateFlow<T: Any>(private val origin: StateFlow<T>) : StateFlow<T> by origin {

    /**
     * Function for subscribing to updates that [origin] state flow emits.
     *
     * @param block lambda that provides an emitted [T] items.
     * @return [Closeable] for possibility to unsubscribe.
     */
    @RestrictedFlowSubscription
    fun subscribe(block: (T) -> Unit): Closeable = subscribeToFlow(block)
}