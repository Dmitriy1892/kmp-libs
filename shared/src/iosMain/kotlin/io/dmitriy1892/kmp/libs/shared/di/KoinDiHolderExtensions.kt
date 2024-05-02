package io.dmitriy1892.kmp.libs.shared.di

import io.github.dmitriy1892.kmp.libs.utils.platform.PlatformConfiguration

fun KoinDiHolder.Companion.getInstance(): KoinDiHolder = this.getInstance(PlatformConfiguration())