package com.github.dmitriy1892.kmplibs.mvvm.core.internal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import kotlin.reflect.KClass

internal class ViewModelProviderWrapper(
    private val viewModelStore: ViewModelStore = ViewModelStore(),
    viewModelFactory: ViewModelProvider.Factory
) {
    private val provider = ViewModelProvider.create(viewModelStore, viewModelFactory)

    fun <T: ViewModel> get(
        modelClass: KClass<T>,
        key: String? = null
    ): T = key?.let { provider[it, modelClass] } ?: provider[modelClass]

    fun removeAllViewModels() {
        viewModelStore.clear()
    }
}