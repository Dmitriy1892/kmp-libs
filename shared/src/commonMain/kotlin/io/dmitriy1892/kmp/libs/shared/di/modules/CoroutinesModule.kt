package io.dmitriy1892.kmp.libs.shared.di.modules

import io.dmitriy1892.kmp.libs.shared.di.qualifiers.AppCoroutineScope
import io.dmitriy1892.kmp.libs.shared.di.qualifiers.DefaultDispatcher
import io.dmitriy1892.kmp.libs.shared.di.qualifiers.IoDispatcher
import io.dmitriy1892.kmp.libs.shared.di.qualifiers.MainDispatcher
import io.dmitriy1892.kmp.libs.shared.di.qualifiers.MainImmediateDispatcher
import io.dmitriy1892.kmp.libs.shared.di.qualifiers.UnconfinedDispatcher
import io.dmitriy1892.kmp.libs.shared.di.qualifiers.ViewModelCoroutineScope
import io.github.dmitriy1892.kmp.libs.utils.coroutines.dispatcher.CoroutineDispatcherProvider
import io.github.dmitriy1892.kmp.libs.utils.coroutines.dispatcher.DefaultCoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class CoroutinesModule {

    @Single
    fun provideCoroutineDispatcherProvider(): CoroutineDispatcherProvider =
        DefaultCoroutineDispatcherProvider

    @MainDispatcher
    @Single
    fun provideMainDispatcher(
        dispatcherProvider: CoroutineDispatcherProvider
    ): CoroutineDispatcher = dispatcherProvider.main

    @MainImmediateDispatcher
    @Single
    fun provideMainImmediateDispatcher(
        dispatcherProvider: CoroutineDispatcherProvider
    ): CoroutineDispatcher = dispatcherProvider.mainImmediate

    @IoDispatcher
    @Single
    fun provideIoDispatcher(
        dispatcherProvider: CoroutineDispatcherProvider
    ): CoroutineDispatcher = dispatcherProvider.io

    @DefaultDispatcher
    @Single
    fun provideDefaultDispatcher(
        dispatcherProvider: CoroutineDispatcherProvider
    ): CoroutineDispatcher = dispatcherProvider.default

    @UnconfinedDispatcher
    @Single
    fun provideUnconfinedDispatcher(
        dispatcherProvider: CoroutineDispatcherProvider
    ): CoroutineDispatcher = dispatcherProvider.unconfined

    @ViewModelCoroutineScope
    @Factory
    fun provideViewModelScope(
        @MainImmediateDispatcher dispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(dispatcher + SupervisorJob())

    @AppCoroutineScope
    @Single
    fun provideAppCoroutineScope(
        @MainImmediateDispatcher dispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(dispatcher + SupervisorJob())

}