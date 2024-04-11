package io.github.dmitriy1892.kmp.libs.utils.coroutines.dispatcher

import kotlinx.coroutines.MainCoroutineDispatcher
import kotlin.concurrent.Volatile

@Volatile
private var isImmediateSupported: Boolean = true

/**
 * Provides access to the immediate coroutine dispatcher
 * or fallbacks to the main dispatcher if platform doesn't support immediate dispatcher.
 */
val MainCoroutineDispatcher.immediateOrFallback: MainCoroutineDispatcher
    get() {
        if (isImmediateSupported) {
            try {
                return immediate
            } catch (_: Throwable) {
                isImmediateSupported = false
            }
        }

        return this
    }