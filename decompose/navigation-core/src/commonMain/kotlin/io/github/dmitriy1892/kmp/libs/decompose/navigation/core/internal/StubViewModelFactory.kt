package io.github.dmitriy1892.kmp.libs.decompose.navigation.core.internal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import kotlin.reflect.KClass

object StubViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        throw UnsupportedOperationException("No ViewModelProvider.Factory provided.")
    }
}