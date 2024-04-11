package io.github.dmitriy1892.kmp.libs.utils.coroutines.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first

/**
 * Event mutable shared flow is a [MutableSharedFlow] wrapper.
 * It can be used if you want to implement shared one-time events or something similar.
 *
 * @property sharedFlow origin [MutableSharedFlow] that need to wrap.
 */
class EventMutableSharedFlow<T>(
    private val sharedFlow: MutableSharedFlow<T> = MutableSharedFlow()
) : MutableSharedFlow<T> by sharedFlow {

    /**
     * Function for emitting values into the origin [sharedFlow].
     * Function suspends is no subscribers presents
     * and it will wait until at leas one subscriber appears.
     *
     * @param value [T] value that need to emit into the origin [sharedFlow]
     */
    override suspend fun emit(value: T) {
        sharedFlow.subscriptionCount.first { it > 0 }
        sharedFlow.emit(value)
    }

    /**
     * Accepts the given [collector] and [emits][FlowCollector.emit] values into it.
     * To emit values from a shared flow into a specific collector, either `collector.emitAll(flow)` or `collect { ... }`
     * SAM-conversion can be used.
     *
     * **A shared flow never completes**. A call to [Flow.collect] or any other terminal operator
     * on a shared flow never completes normally.
     *
     * It is guaranteed that, by the time the first suspension happens, [collect] has already subscribed to the
     * [SharedFlow] and is eligible for receiving emissions. In particular, the following code will always print `1`:
     * ```
     * val flow = MutableSharedFlow<Int>()
     * launch(start = CoroutineStart.UNDISPATCHED) {
     *   flow.collect { println(1) }
     * }
     * flow.emit(1)
     * ```
     *
     * @see [Flow.collect] for implementation and inheritance details.
     */
    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        sharedFlow.collect(collector)
    }
}