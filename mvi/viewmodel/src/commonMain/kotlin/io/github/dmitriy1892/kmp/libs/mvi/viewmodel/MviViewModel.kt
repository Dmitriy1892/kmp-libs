package io.github.dmitriy1892.kmp.libs.mvi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.dmitriy1892.kmp.libs.mvi.core.Store
import io.github.dmitriy1892.kmp.libs.mvi.core.StoreProvider
import io.github.dmitriy1892.kmp.libs.mvi.core.extensions.store
import io.github.dmitriy1892.kmp.libs.mvi.core.model.StoreConfig
import io.github.dmitriy1892.kmp.libs.utils.coroutines.dispatcher.immediateOrFallback
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class MviViewModel<State: Any, SideEffect: Any> : ViewModel, StoreProvider<State, SideEffect> {

    constructor() : super()

    constructor(
        viewModelScope: CoroutineScope,
        exceptionHandler: CoroutineExceptionHandler? = null
    ) : super(viewModelScope) {
        this.exceptionHandler = exceptionHandler
    }

    protected var exceptionHandler: CoroutineExceptionHandler? = null

    protected abstract val initialState: State

    @OptIn(ExperimentalStdlibApi::class)
    override val store: Store<State, SideEffect> by lazy {
        viewModelScope.store(
            initialState = initialState,
            storeConfig = StoreConfig(
                eventsDispatcher = viewModelScope.coroutineContext[CoroutineDispatcher]
                    ?: Dispatchers.Main.immediateOrFallback,
                exceptionHandler = exceptionHandler
            )
        )
    }

    override fun onCleared() {
        store.cancel()
        super.onCleared()
    }
}