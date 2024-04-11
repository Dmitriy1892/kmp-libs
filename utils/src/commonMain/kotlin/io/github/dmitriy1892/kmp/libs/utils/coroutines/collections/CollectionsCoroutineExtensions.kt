package io.github.dmitriy1892.kmp.libs.utils.coroutines.collections

import io.github.dmitriy1892.kmp.libs.utils.coroutines.invokeAsync
import kotlinx.coroutines.awaitAll
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Map [Iterable] collection asynchronous.
 *
 * @param context [CoroutineContext].
 * @param transform item transformation lambda from [T] to [R] type.
 * @return [List] of mapped from [T] to [R] items.
 */
suspend fun <T, R> Iterable<T>.mapAsync(
    context: CoroutineContext = EmptyCoroutineContext,
    transform: suspend (T) -> R
): List<R> =
    map { value -> invokeAsync(context) { transform(value) } }.awaitAll()

/**
 * Perform the given [block] action asynchronous for each element in [Iterable].
 *
 * @param context [CoroutineContext].
 * @param block action block for every [T] item on the [Iterable].
 */
suspend fun <T> Iterable<T>.forEachAsync(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend (T) -> Unit
) {
    mapAsync(context) { block(it) }
}