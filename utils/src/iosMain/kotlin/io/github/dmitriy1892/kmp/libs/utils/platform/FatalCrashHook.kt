package io.github.dmitriy1892.kmp.libs.utils.platform

import kotlin.experimental.ExperimentalNativeApi

object FatalCrashHook {

    @OptIn(ExperimentalNativeApi::class)
    fun doBeforeAppCrashing(callback: (Throwable) -> Unit) {
        setUnhandledExceptionHook {
            callback(it)
            println("---------------------KOTLIN MULTIPLATFORM ERROR STACKTRACE START---------------------")
            it.printStackTrace()
            println("---------------------KOTLIN MULTIPLATFORM ERROR STACKTRACE END---------------------")
        }
    }
}