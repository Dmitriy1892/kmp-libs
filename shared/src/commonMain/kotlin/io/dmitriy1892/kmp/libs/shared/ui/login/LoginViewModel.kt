package io.dmitriy1892.kmp.libs.shared.ui.login

import io.dmitriy1892.kmp.libs.shared.di.qualifiers.IoDispatcher
import io.dmitriy1892.kmp.libs.shared.di.qualifiers.ViewModelCoroutineScope
import io.github.dmitriy1892.kmp.libs.mvi.core.extensions.intent
import io.github.dmitriy1892.kmp.libs.mvi.viewmodel.MviViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory
class LoginViewModel(
    @ViewModelCoroutineScope scope: CoroutineScope,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MviViewModel<Int, Unit>(scope) {

    override val initialState: Int = (1..100).random()

    init {
        intent {
            withContext(ioDispatcher) {
                delay(4000)
            }

            reduceState { state -> state + 3 }
        }
    }
}