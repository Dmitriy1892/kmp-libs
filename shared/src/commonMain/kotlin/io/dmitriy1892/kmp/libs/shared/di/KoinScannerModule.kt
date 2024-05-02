package io.dmitriy1892.kmp.libs.shared.di

import io.dmitriy1892.kmp.libs.shared.di.modules.CoroutinesModule
import io.dmitriy1892.kmp.libs.shared.di.modules.ViewModelsModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [ViewModelsModule::class, CoroutinesModule::class])
@ComponentScan("io.dmitriy1892.kmp.libs.shared")
class KoinScannerModule