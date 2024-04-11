package io.github.dmitriy1892.kmp.libs.utils.coroutines.flow

@RequiresOptIn(
    message = "Avoid using this subscription inside Kotlin code. " +
            "Use it only if you want to subscribe to flow from iOS code." +
            "Don't forget to unsubscribe manually from flow when you don't want to listen an emitted values",
    level = RequiresOptIn.Level.WARNING
)
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class RestrictedFlowSubscription