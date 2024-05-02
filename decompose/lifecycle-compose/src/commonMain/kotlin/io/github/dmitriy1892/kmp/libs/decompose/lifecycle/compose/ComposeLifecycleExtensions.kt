package io.github.dmitriy1892.kmp.libs.decompose.lifecycle.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.produceState
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.coroutines.repeatOnLifecycle
import com.arkivanov.essenty.lifecycle.coroutines.withLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

/**
 * [CompositionLocal][androidx.compose.runtime.CompositionLocal] for [LifecycleOwner] access from [Composable] functions.
 */
@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
val LocalLifecycleOwner = compositionLocalOf<LifecycleOwner> { error("No LifecycleOwner provided") }

/**
 * Extension function for converting a [StateFlow] to the compose [State] and observing it changes
 * only if [Composable] function state is on [Lifecycle] [minActiveState].
 *
 * @param lifecycleOwner [LifecycleOwner] holder of [Lifecycle].
 * @param minActiveState minimal active [Lifecycle.State] for the [StateFlow] changes tracking.
 * @param context [CoroutineContext].
 * @return [State].
 */
@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
@Composable
fun <T> StateFlow<T>.collectAsStateWithLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    context: CoroutineContext = EmptyCoroutineContext
): State<T> = collectAsStateWithLifecycle(
    initialValue = value,
    lifecycleOwner = lifecycleOwner,
    minActiveState = minActiveState,
    context = context
)

/**
 * Extension function for converting a [Flow] to the compose [State] and observing it changes
 * only if [Composable] function state is on [Lifecycle] [minActiveState].
 *
 * @param initialValue [T] initial [State] value.
 * @param lifecycleOwner [LifecycleOwner] holder of [Lifecycle].
 * @param minActiveState minimal active [Lifecycle.State] for the [StateFlow] changes tracking.
 * @param context [CoroutineContext].
 * @return [State].
 */
@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
@Composable
fun <T> Flow<T>.collectAsStateWithLifecycle(
    initialValue: T,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    context: CoroutineContext = EmptyCoroutineContext
): State<T> {
    return produceState(initialValue, this, lifecycleOwner, minActiveState, context) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
            if (context == EmptyCoroutineContext) {
                this@collectAsStateWithLifecycle.collect { this@produceState.value = it }
            } else withContext(context) {
                this@collectAsStateWithLifecycle.collect { this@produceState.value = it }
            }
        }
    }
}

/**
 * Extension function for observing a [Flow] emissions as side effect events like Toast
 * or [Snackbar][androidx.compose.material.Snackbar] displaying, or navigation event.
 * Flow will observe emissions only if [Lifecycle.State] was at least in [minActiveState].
 *
 * @param lifecycleOwner [LifecycleOwner] holder of [Lifecycle].
 * @param minActiveState minimal active [Lifecycle.State] for the [StateFlow] changes tracking.
 * @param onEach callback for action producing based on received [T] side effect.
 */
@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
@Composable
fun <T> Flow<T>.collectSideEffectWithLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    onEach: (T) -> Unit
) {
    LaunchedEffect(null) {
        this@collectSideEffectWithLifecycle
            .withLifecycle(lifecycleOwner.lifecycle, minActiveState)
            .onEach { onEach(it) }
            .launchIn(this)
    }
}

/**
 * Extension function for observing a [Flow] emissions as side effect events like Toast
 * or [Snackbar][androidx.compose.material.Snackbar] displaying, or navigation event.
 *
 * @param onEach callback for action producing based on received [T] side effect.
 */
@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
@Composable
fun <T> Flow<T>.collectSideEffect(onEach: (T) -> Unit)  {
    LaunchedEffect(null) {
        this@collectSideEffect
            .onEach { onEach(it) }
            .launchIn(this)
    }
}