package io.github.dmitriy1892.kmp.libs.utils.coroutines.mutex

@RequiresOptIn(
    message = "Locks the calling thread. Avoid using it. Use only if you know what you are doing.",
    level = RequiresOptIn.Level.WARNING
)
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
annotation class RestrictedMutexApi
