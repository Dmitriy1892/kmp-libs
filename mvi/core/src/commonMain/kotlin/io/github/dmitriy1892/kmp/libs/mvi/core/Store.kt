package io.github.dmitriy1892.kmp.libs.mvi.core

import io.github.dmitriy1892.kmp.libs.mvi.core.model.StoreConfig
import io.github.dmitriy1892.kmp.libs.mvi.core.model.StoreContext
import io.github.dmitriy1892.kmp.libs.utils.coroutines.flow.WrappedFlow
import io.github.dmitriy1892.kmp.libs.utils.coroutines.flow.WrappedStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

/**
 * Store is a central class of the KMM-MVI library. It's an MVI container with inputs and outputs.
 *
 * @param State store state type.
 * @param SideEffect store side effects type (one-shot events of store).
 */
interface Store<State: Any, SideEffect: Any> {

    /**
     * StoreConfig that can be used for [scope] configuring
     */
    val config: StoreConfig

    /**
     * Coroutine scope that can be used for suspend operations in store.
     *
     * Strongly recommend to use it for all internal operations inside [Store].
     *
     * Strongly recommend to configure it's [CoroutineScope.coroutineContext] with defined [config] parameters,
     * i.e. [StoreConfig.eventsDispatcher] and [StoreConfig.exceptionHandler].
     */
    val scope: CoroutineScope

    /**
     * [Store] output point.
     *
     * WrappedStateFlow with current state.
     */
    val stateFlow: WrappedStateFlow<State>

    /**
     * [Store] output point.
     *
     * [WrappedFlow] with [SideEffect]'s - used for one-shot events, i.e. alerts/dialogs displaying,
     * navigation events, etc.
     */
    val sideEffectFlow: WrappedFlow<SideEffect>

    /**
     * [Store] input point.
     *
     * Process user [intent]
     *
     * @param intent suspend function with user logic that used for [State] transformation
     * or [SideEffect] producing.
     */
    suspend fun processIntent(intent: suspend StoreContext<State, SideEffect>.() -> Unit)

    /**
     * Function for [Store] logic cancelling before garbage collecting.
     * Used for [scope] cancelling by default.
     */
    fun cancel() {
        scope.cancel()
    }
}