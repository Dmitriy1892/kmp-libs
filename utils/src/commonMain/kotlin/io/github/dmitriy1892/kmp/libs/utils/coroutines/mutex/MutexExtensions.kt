package io.github.dmitriy1892.kmp.libs.utils.coroutines.mutex

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Locks the current thread until [action] was end his work.
 *
 * @param action suspendable action that need to execute
 * @return [T]
 */
@RestrictedMutexApi
inline fun <T> Mutex.withThreadLock(crossinline action: suspend () -> T): T {
    return runBlocking { this@withThreadLock.withLock { action() } }
}