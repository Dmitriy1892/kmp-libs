package io.github.dmitriy1892.kmp.libs.mvi.core.extensions

import io.github.dmitriy1892.kmp.libs.mvi.core.Store
import io.github.dmitriy1892.kmp.libs.mvi.core.annotation.KmpMviDsl
import io.github.dmitriy1892.kmp.libs.mvi.core.internal.StoreImpl
import io.github.dmitriy1892.kmp.libs.mvi.core.model.StoreConfig
import io.github.dmitriy1892.kmp.libs.mvi.core.model.StoreContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Create a new store based on [CoroutineScope].
 *
 * @param initialState initial [State] for [Store.stateFlow].
 * @param storeConfig [StoreConfig] for [Store.scope] and [Store.sideEffectFlow] configuring.
 *
 * @return [Store] that configured with [initialState] and [storeConfig] parameters.
 */
@KmpMviDsl
fun <State: Any, SideEffect: Any> CoroutineScope.store(
    initialState: State,
    storeConfig: StoreConfig = StoreConfig()
): Store<State, SideEffect> = StoreImpl(
    initialState = initialState,
    parentScope = this,
    config = storeConfig
)

/**
 * DSL function for processing a user intent.
 *
 * @param block block that contains a logic for user event reaction.
 *
 * @return [Job].
 *
 * Sample of usage:
 * ```
 * fun changeCounter(currentValue: Int) = intent {
 *     sendSideEffect(CounterSideEffect.ShowIncrementStartedToast)
 *
 *     delay(3000L) // long-running task simulating
 *
 *     reduceState { state -> state.copy(incrementValue = currentValue) }
 *
 *     sendSideEffect(CounterSideEffect.ShowIncrementFinishedToast)
 * }
 * ```
 */
@KmpMviDsl
fun <State: Any, SideEffect: Any> Store<State, SideEffect>.intent(
    block: suspend StoreContext<State, SideEffect>.() -> Unit
): Job = this.scope.launch {
    this@intent.processIntent(block)
}

/**
 * Function for processing a user intent that can be used inside another suspend function.
 *
 * WARNING: this function is not calls on the [Store.scope] context by default!!!
 *
 * @param block block that contains a logic for user event reaction.
 */
suspend inline fun <State: Any, SideEffect: Any> Store<State, SideEffect>.suspendIntent(
    crossinline block: suspend StoreContext<State, SideEffect>.() -> Unit
): Unit = this.processIntent { block(this) }