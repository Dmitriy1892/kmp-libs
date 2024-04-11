package io.github.dmitriy1892.kmp.libs.utils.coroutines.mutex

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.coroutines.cancellation.CancellationException

/**
 * Class-wrapper for thread-safe access to data [T]
 *
 * @constructor
 *
 * @param initialValue initial value
 */
class MutexVariable<T>(initialValue: T) {

    /**
     * [value] - field for storing a [T] variable
     */
    private var value: T = initialValue

    /**
     * [mutex] - [Mutex] object for thread-safe access to [value]
     */
    private val mutex = Mutex()

    /**
     * Thread-safe set new [value] into [MutexVariable.value] field
     *
     * @param value new [T] value
     */
    suspend fun set(value: T) {
        mutex.withLock { this.value = value }
    }

    /**
     * Thread-safe get [MutexVariable.value]
     *
     * @return [value] - [MutexVariable.value]
     */
    suspend fun get(): T = mutex.withLock { value }

    /**
     * Get [MutexVariable.value] as non-null
     *
     * @return non-nullable [T] or throws an [IllegalArgumentException] if [MutexVariable.value] is null
     * @throws [IllegalArgumentException] if [MutexVariable.value] is null
     * @throws [CancellationException]
     */
    @Throws(IllegalArgumentException::class, CancellationException::class)
    suspend fun getNotNull(): T & Any = mutex.withLock { requireNotNull(value)  }

    /**
     * Get [MutexVariable.value] or save and get [defaultValue] if [MutexVariable.value] is null.
     *
     * @param defaultValue lambda for non-nullable default [T] receiving
     * @return non-nullable [T]
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun getOrPut(defaultValue: suspend () -> T & Any): T & Any {
        return mutex.withLock {
            if (value == null) {
                val defaultVal = defaultValue()
                value = defaultVal
                defaultVal
            } else {
                value as (T & Any)
            }
        }
    }

    /**
     * Thread-safe update [MutexVariable.value]
     *
     * @param transformation lambda function for transforming [MutexVariable.value]
     * to a new one with [T] type
     */
    suspend fun update(transformation: suspend (T) -> T) {
        mutex.withLock { value = transformation(value) }
    }
}