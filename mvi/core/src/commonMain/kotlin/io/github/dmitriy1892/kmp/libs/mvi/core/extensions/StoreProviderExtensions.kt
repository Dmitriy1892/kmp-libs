package io.github.dmitriy1892.kmp.libs.mvi.core.extensions

import io.github.dmitriy1892.kmp.libs.mvi.core.Store
import io.github.dmitriy1892.kmp.libs.mvi.core.StoreProvider
import io.github.dmitriy1892.kmp.libs.mvi.core.annotation.KmpMviDsl
import io.github.dmitriy1892.kmp.libs.mvi.core.model.StoreContext
import io.github.dmitriy1892.kmp.libs.utils.coroutines.flow.WrappedFlow
import io.github.dmitriy1892.kmp.libs.utils.coroutines.flow.WrappedStateFlow
import kotlinx.coroutines.Job

/**
 * [Store] output point.
 *
 * WrappedStateFlow with current state.
 */
val <State: Any, SideEffect: Any> StoreProvider<State, SideEffect>.stateFlow: WrappedStateFlow<State>
    get() = this.store.stateFlow

/**
 * [Store] output point.
 *
 * [WrappedFlow] with [SideEffect]'s - used for one-shot events, i.e. alerts/dialogs displaying,
 * navigation events, etc.
 */
val <State: Any, SideEffect: Any> StoreProvider<State, SideEffect>.sideEffectFlow: WrappedFlow<SideEffect>
    get() = this.store.sideEffectFlow

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
fun <State: Any, SideEffect: Any> StoreProvider<State, SideEffect>.intent(
    block: suspend StoreContext<State, SideEffect>.() -> Unit
): Job = this.store.intent(block)

/**
 * Function for processing a user intent that can be used inside another suspend function.
 *
 * WARNING: this function is not calls on the [Store.scope] context by default!!!
 *
 * @param block block that contains a logic for user event reaction.
 */
suspend inline fun <State: Any, SideEffect: Any> StoreProvider<State, SideEffect>.suspendIntent(
    crossinline block: suspend StoreContext<State, SideEffect>.() -> Unit
): Unit = this.store.suspendIntent(block)