package io.dmitriy1892.kmp.libs.shared.di.qualifiers

import org.koin.core.annotation.Named

@Named("MainDispatcher")
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
annotation class MainDispatcher

@Named("MainImmediateDispatcher")
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
annotation class MainImmediateDispatcher

@Named("IoDispatcher")
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
annotation class IoDispatcher

@Named("DefaultDispatcher")
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
annotation class DefaultDispatcher

@Named("UnconfinedDispatcher")
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
annotation class UnconfinedDispatcher