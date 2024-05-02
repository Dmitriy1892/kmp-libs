package io.dmitriy1892.kmp.libs.shared.di.qualifiers

import org.koin.core.annotation.Named

@Named("ViewModelScope")
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
annotation class ViewModelCoroutineScope

@Named("ApplicationScope")
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
annotation class AppCoroutineScope
