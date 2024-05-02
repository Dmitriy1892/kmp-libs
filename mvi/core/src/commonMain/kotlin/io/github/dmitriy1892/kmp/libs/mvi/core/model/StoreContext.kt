package io.github.dmitriy1892.kmp.libs.mvi.core.model

/**
 * Class for providing a set of functions
 * for [Store][io.github.dmitriy1892.kmp.libs.mvi.core.Store]'s [SideEffect]s sending,
 * [State] getting and transformation.
 */
class StoreContext<State: Any, SideEffect: Any>(
    val sendSideEffect: suspend (SideEffect) -> Unit,
    val getState: () -> State,
    val reduceState: suspend ((State) -> State) -> Unit
) {

    /**
     * Value with current [State].
     *
     * WARNING: Don't use it inside [reduceState] function.
     */
    val state: State
        get() = getState()
}