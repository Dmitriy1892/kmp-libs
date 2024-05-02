package io.github.dmitriy1892.kmp.libs.decompose.navigation.core.internal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.CreationExtras
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlin.reflect.KClass

private const val KEY_VIEW_MODEL = "io.github.dmitriy1892.kmp.libs.navigation.core.KEY_VIEW_MODEL"

/**
 * Class that manages a [ViewModel] lifecycle and holds it instance.
 *
 * @param viewModelFactoryProvider [ViewModelProvider.Factory] for the [ViewModel] creation.
 */
internal class ViewModelInstanceKeeper(
    viewModelFactoryProvider: Lazy<ViewModelProvider.Factory>
) : InstanceKeeper.Instance {

    private val viewModelStore: ViewModelStore by lazy { ViewModelStore() }

    private val viewModelFactory: ViewModelProvider.Factory by viewModelFactoryProvider

    /**
     * Returns a [ViewModel] instance if it already created,
     * or creates and returns if [ViewModel] wasn't created earlier.
     *
     * @param clazz [KClass] of [ViewModel] that required for the [ViewModel] instance creation.
     * @return [ViewModel] of [T] type
     */
    @Suppress("UNCHECKED_CAST")
    fun <T: ViewModel> getViewModel(clazz: KClass<T>): T {
        val instance = viewModelStore[KEY_VIEW_MODEL] as? T
        return instance ?: viewModelFactory.create(clazz, CreationExtras.Empty).also {
            viewModelStore.put(KEY_VIEW_MODEL, it)
        }
    }

    /**
     * Called at the end of the [InstanceKeeper]'s scope.
     */
    override fun onDestroy() {
        viewModelStore.clear()
    }
}