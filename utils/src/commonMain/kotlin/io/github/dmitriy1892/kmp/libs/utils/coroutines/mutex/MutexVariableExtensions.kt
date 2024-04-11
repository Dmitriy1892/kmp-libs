package io.github.dmitriy1892.kmp.libs.utils.coroutines.mutex

import kotlinx.coroutines.runBlocking

@RestrictedMutexApi
fun <T> MutexVariable<T>.setBlocking(value: T): Unit = runBlocking { this@setBlocking.set(value) }

@RestrictedMutexApi
fun <T> MutexVariable<T>.getBlocking(): T = runBlocking { this@getBlocking.get() }

@RestrictedMutexApi
fun <T> MutexVariable<T>.getNotNullBlocking(): T & Any = runBlocking {
    this@getNotNullBlocking.getNotNull()
}

@RestrictedMutexApi
fun <T> MutexVariable<T>.getOrPutBlocking(
    defaultValue: suspend () -> T & Any
): T & Any = runBlocking { this@getOrPutBlocking.getOrPut(defaultValue) }

@RestrictedMutexApi
fun <T> MutexVariable<T>.updateBlocking(transformation: suspend (T) -> T): Unit = runBlocking {
    this@updateBlocking.update(transformation)
}