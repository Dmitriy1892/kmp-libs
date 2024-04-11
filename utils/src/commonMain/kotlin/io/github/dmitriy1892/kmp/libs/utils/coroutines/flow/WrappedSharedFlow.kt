package io.github.dmitriy1892.kmp.libs.utils.coroutines.flow

import io.github.dmitriy1892.kmp.libs.utils.Closeable
import kotlinx.coroutines.flow.SharedFlow

/**
 * Wrapper over [SharedFlow] for possibility to using flow from iOS without pain.
 *
 * @property origin origin kotlin [SharedFlow] that need to wrap
 */
class WrappedSharedFlow<T: Any>(private val origin: SharedFlow<T>) : SharedFlow<T> by origin {

    /**
     * Function for subscribing to updates that [origin] shared flow emits.
     *
     * @param block lambda that provides an emitted [T] items.
     * @return [Closeable] for possibility to unsubscribe.
     */
    @RestrictedFlowSubscription
    fun subscribe(block: (T) -> Unit): Closeable = subscribeToFlow(block)
}