package io.github.dmitriy1892.kmp.libs.utils.platform

import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
internal actual fun isDebug(): Boolean = Platform.isDebugBinary