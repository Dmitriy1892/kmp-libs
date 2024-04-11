package io.github.dmitriy1892.kmp.libs.utils

/**
 * Closeable interface that can be used for something that need to close after work,
 * for example input/output streams, subscriptions etc.
 */
interface Closeable {

    fun close()
}