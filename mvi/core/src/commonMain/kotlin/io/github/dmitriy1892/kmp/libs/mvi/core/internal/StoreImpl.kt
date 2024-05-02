package io.github.dmitriy1892.kmp.libs.mvi.core.internal

import io.github.dmitriy1892.kmp.libs.mvi.core.model.StoreConfig
import io.github.dmitriy1892.kmp.libs.mvi.core.model.StoreContext
import io.github.dmitriy1892.kmp.libs.mvi.core.Store
import io.github.dmitriy1892.kmp.libs.utils.coroutines.flow.WrappedFlow
import io.github.dmitriy1892.kmp.libs.utils.coroutines.flow.WrappedStateFlow
import io.github.dmitriy1892.kmp.libs.utils.coroutines.flow.asWrappedFlow
import io.github.dmitriy1892.kmp.libs.utils.coroutines.flow.asWrappedStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.plus

/**
 * Default implementation of [Store].
 * Used in extension functions for simplifying a [Store] creation process.
 */
internal class StoreImpl<State: Any, SideEffect: Any>(
    initialState: State,
    parentScope: CoroutineScope,
    override val config: StoreConfig
) : Store<State, SideEffect> {

    override val scope = (parentScope + config.eventsDispatcher).apply {
        config.exceptionHandler?.let { coroutineExceptionHandler ->
            plus(coroutineExceptionHandler)
        }
    }

    private val _stateFlow = MutableStateFlow(initialState)
    override val stateFlow: WrappedStateFlow<State> = _stateFlow.asWrappedStateFlow()

    private val _sideEffectFlow = Channel<SideEffect>()
    override val sideEffectFlow: WrappedFlow<SideEffect> =
        _sideEffectFlow.receiveAsFlow().asWrappedFlow()

    private val scopeContext = StoreContext<State, SideEffect>(
        sendSideEffect = { sideEffect -> _sideEffectFlow.send(sideEffect) },
        getState = { _stateFlow.value },
        reduceState = { reducer -> _stateFlow.update(reducer) }
    )

    override suspend fun processIntent(intent: suspend StoreContext<State, SideEffect>.() -> Unit) {
        intent.invoke(scopeContext)
    }
}