package io.github.dmitriy1892.kmp.libs.mvvm.core

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import io.github.dmitriy1892.kmp.libs.mvvm.core.internal.ViewModelProviderWrapper
import kotlin.reflect.KClass

class ViewModelProviderHolder(
    private val defaultViewModelFactory: ViewModelProvider.Factory
) {

    private val providersMap = mutableMapOf<String, ViewModelProviderWrapper>()

    @MainThread
    inline fun <reified T: ViewModel> getOrCreateViewModel(
        key: String? = null,
        factory: ViewModelProvider.Factory? = null,
        store: ViewModelStore? = null
    ): T = getOrCreateViewModel(T::class, key, factory, store)

    @MainThread
    fun <T: ViewModel> getOrCreateViewModel(
        modelClass: KClass<T>,
        key: String? = null,
        factory: ViewModelProvider.Factory? = null,
        store: ViewModelStore? = null
    ): T {
        val compositeKey = modelClass.getCompositeKey(key)
        val wrapper = providersMap[compositeKey]

        return if (wrapper == null) {
            val newWrapper = ViewModelProviderWrapper(
                viewModelStore = store ?: ViewModelStore(),
                viewModelFactory = factory ?: defaultViewModelFactory
            )

            providersMap[compositeKey] = newWrapper

            newWrapper.get(modelClass, key)
        } else {
            wrapper.get(modelClass, key)
        }
    }

    @MainThread
    inline fun <reified T: ViewModel> remove(key: String? = null): Unit = remove(T::class, key)

    @MainThread
    fun <T: ViewModel> remove(
        modelClass: KClass<T>,
        key: String? = null
    ) {
        val compositeKey = modelClass.getCompositeKey(key)
        providersMap.remove(compositeKey)?.removeAllViewModels()
    }
}

private fun <T: ViewModel> KClass<T>.getCompositeKey(
    key: String?
): String = "$key:$qualifiedName"