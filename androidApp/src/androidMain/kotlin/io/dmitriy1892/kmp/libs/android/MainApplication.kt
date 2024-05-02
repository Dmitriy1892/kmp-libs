package io.dmitriy1892.kmp.libs.android

import android.app.Application
import io.dmitriy1892.kmp.libs.shared.di.KoinDiHolder
import io.github.dmitriy1892.kmp.libs.utils.platform.PlatformConfiguration

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinDiHolder.getInstance(PlatformConfiguration(this))
    }
}