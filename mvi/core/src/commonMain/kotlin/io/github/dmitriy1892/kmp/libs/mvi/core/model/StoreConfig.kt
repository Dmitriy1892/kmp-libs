package io.github.dmitriy1892.kmp.libs.mvi.core.model

import io.github.dmitriy1892.kmp.libs.utils.coroutines.dispatcher.immediateOrFallback
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

/**
 * StoreConfig - class with [Store][io.github.dmitriy1892.kmp.libs.mvi.core.Store] configuration.
 * Used for [Store.scope][io.github.dmitriy1892.kmp.libs.mvi.core.Store.scope] configuring.
 */
data class StoreConfig(
    val eventsDispatcher: CoroutineDispatcher = Dispatchers.Main.immediateOrFallback,
    val exceptionHandler: CoroutineExceptionHandler? = null
)