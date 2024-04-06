package com.github.dmitriy1892.kmplibs.mvvm.koin.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import org.koin.core.component.KoinComponent
import kotlin.reflect.KClass

class KoinViewModelFactory : ViewModelProvider.Factory, KoinComponent {

    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        return getKoin().get(modelClass)
    }
}