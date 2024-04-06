package com.github.dmitriy1892.kmplibs.mvvm.koin.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.ParametersHolder
import kotlin.reflect.KClass

class KoinAssistedViewModelFactory(
    private val assistedArgs: ParametersHolder
) : ViewModelProvider.Factory, KoinComponent {

    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        return getKoin().get(modelClass) { assistedArgs }
    }
}