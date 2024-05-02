package io.github.dmitriy1892.kmp.libs.decompose.navigation.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import io.github.dmitriy1892.kmp.libs.decompose.navigation.core.internal.ViewModelInstanceKeeper
import kotlin.reflect.KClass

/**
 * Navigation destination - class that holds [ViewModel] instance and can represent a UI screen.
 *
 * @param componentContext [ComponentContext]
 */
abstract class Destination(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    /**
     * View model factory for creating a [ViewModel] instance.
     */
    protected abstract val viewModelFactory: Lazy<ViewModelProvider.Factory>

    /**
     * View model instance keeper that creates and manages the [ViewModel] lifecycle.
     */
    private val viewModelInstanceKeeper: ViewModelInstanceKeeper by lazy {
        instanceKeeper.getOrCreate { ViewModelInstanceKeeper(viewModelFactory) }
    }

    /**
     * Function provides a single instance of [ViewModel] per [Destination] instance.
     *
     * @return [ViewModel] of [T] type.
     */
    inline fun <reified T: ViewModel> decomposeViewModel(): T =
        decomposeViewModel(T::class)

    /**
     * Function provides a single instance of [ViewModel] per [Destination] instance.
     *
     * @param clazz [KClass] of [ViewModel] that required for the [ViewModel] instance creation.
     * @return [ViewModel] of [T] type.
     */
    fun <T: ViewModel> decomposeViewModel(clazz: KClass<T>): T =
        viewModelInstanceKeeper.getViewModel(clazz)
}