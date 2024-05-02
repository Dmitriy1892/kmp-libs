package io.dmitriy1892.kmp.libs.shared.di.modules

import androidx.lifecycle.ViewModelProvider
import io.github.dmitriy1892.kmp.libs.mvvm.koin.factory.KoinViewModelFactory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class ViewModelsModule {

    @Single
    fun provideViewModelFactory(): ViewModelProvider.Factory = KoinViewModelFactory()

}