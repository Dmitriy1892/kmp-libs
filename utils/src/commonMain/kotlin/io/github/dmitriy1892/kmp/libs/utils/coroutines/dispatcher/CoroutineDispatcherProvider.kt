package io.github.dmitriy1892.kmp.libs.utils.coroutines.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Coroutine dispatcher provider interface.
 * Strongly recommend to use it instead of [kotlinx.coroutines.Dispatchers]
 * for easily [CoroutineDispatcher] replacement in tests.
 *
 * @property main main thread coroutine dispatcher. Can defer coroutine execution.
 * @property mainImmediate main thread coroutine dispatcher. Executes coroutine immediate.
 * @property io io thread pool dispatcher.
 * @property default default thread pool dispatcher.
 * @property unconfined unconfined dispatcher.
 */
interface CoroutineDispatcherProvider {
    val main: CoroutineDispatcher
    val mainImmediate: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}