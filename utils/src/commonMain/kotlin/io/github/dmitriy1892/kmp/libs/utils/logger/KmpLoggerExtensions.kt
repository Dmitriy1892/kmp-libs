package io.github.dmitriy1892.kmp.libs.utils.logger

fun Any.logVerbose(message: String, throwable: Throwable? = null): Unit =
    KmpLogger.v(this.getTag(), message, throwable)

fun Any.logDebug(message: String, throwable: Throwable? = null): Unit =
    KmpLogger.d(this.getTag(), message, throwable)

fun Any.logInfo(message: String, throwable: Throwable? = null): Unit =
    KmpLogger.i(this.getTag(), message, throwable)

fun Any.logWarning(message: String, throwable: Throwable? = null): Unit =
    KmpLogger.w(this.getTag(), message, throwable)

fun Any.logError(message: String, throwable: Throwable? = null) =
    KmpLogger.e(this.getTag(), message, throwable)

fun Any.logError(throwable: Throwable) = KmpLogger.e(this.getTag(), throwable)

fun Any.logAssert(message: String, throwable: Throwable? = null) =
    KmpLogger.wtf(this.getTag(), message, throwable)

fun Any.logWtf(message: String, throwable: Throwable? = null) =
    KmpLogger.wtf(this.getTag(), message, throwable)

private fun Any.getTag(): String =
    this::class.simpleName?.ifBlank { KmpLogger.defaultTag } ?: KmpLogger.defaultTag