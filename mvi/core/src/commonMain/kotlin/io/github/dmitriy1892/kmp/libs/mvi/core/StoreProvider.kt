package io.github.dmitriy1892.kmp.libs.mvi.core

/**
 * Wrapper class for [Store]. Used for simplifying store creating/providing.
 * For example, it can be used in cases with [Store] creating using predefined extension functions for ViewModel.
 */
interface StoreProvider<State: Any, SideEffect: Any> {

    /**
     * KMM-MVI [Store] instance.
     * You can use some extension function for instantiating it, for example:
     * ```
     * override val store: Store<State, SideEffect> = coroutineScope.store(initialState)
     * ```
     */
    val store: Store<State, SideEffect>
}