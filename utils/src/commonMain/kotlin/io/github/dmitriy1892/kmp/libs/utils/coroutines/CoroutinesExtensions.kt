package io.github.dmitriy1892.kmp.libs.utils.coroutines

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Invoke the given [block] async
 *
 * @param context [CoroutineContext]
 * @param block suspend block that need to invoke asynchronously
 * @return [Deferred]
 */
suspend fun <T> invokeAsync(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend () -> T
): Deferred<T> = coroutineScope {
    async(context) { block() }
}