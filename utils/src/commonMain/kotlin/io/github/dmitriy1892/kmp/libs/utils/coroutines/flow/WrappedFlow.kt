package io.github.dmitriy1892.kmp.libs.utils.coroutines.flow

import io.github.dmitriy1892.kmp.libs.utils.Closeable
import kotlinx.coroutines.flow.Flow

/**
 * Wrapper over [Flow] for possibility to using flow from iOS without pain.
 *
 * @property origin origin kotlin [Flow] that need to wrap
 */
class WrappedFlow<T: Any>(private val origin: Flow<T>) : Flow<T> by origin {

    /**
     * Function for subscribing to updates that [origin] flow emits.
     *
     * @param block lambda that provides an emitted [T] items.
     * @return [Closeable] for possibility to unsubscribe.
     */
    @RestrictedFlowSubscription
    fun subscribe(block: (T) -> Unit): Closeable = subscribeToFlow(block)
}