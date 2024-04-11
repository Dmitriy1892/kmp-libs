package io.github.dmitriy1892.kmp.libs.utils.datetime

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Function for starting a timer coroutine.
 *
 * @param endTimestampMillis [Long] timestamp in epoch timestamp milliseconds format.
 * @param tickMillis [Long] timer tick step in milliseconds.
 * @param idlingDispatcher [CoroutineDispatcher] that used for idle time between ticks.
 * @param onTickDispatcher [CoroutineDispatcher] that used for returning values inside [onTick] callback.
 * @param onTick callback that returns timestamp in epoch timestamp milliseconds format for every tick.
 * @param onEnd callback that notifies about timer work ending.
 * It will not call if you cancel a returned [Job] before [endTimestampMillis] appearing.
 * @return [Job] - timer coroutine job. Useful for cancelling a timer coroutine.
 */
fun CoroutineScope.startTimer(
    endTimestampMillis: Long,
    tickMillis: Long = 1000L,
    idlingDispatcher: CoroutineDispatcher = Dispatchers.Default,
    onTickDispatcher: CoroutineDispatcher = Dispatchers.Main,
    onTick: suspend (timestamp: Long) -> Unit = {},
    onEnd: suspend () -> Unit
): Job = this.launch(idlingDispatcher) {
    var currentTime = currentTimeMillis()
    while (currentTime < endTimestampMillis) {
        withContext(onTickDispatcher) { onTick(currentTime) }
        delay(tickMillis)
        currentTime = currentTimeMillis()
    }
    onEnd()
}