package io.dmitriy1892.kmp.libs

import io.dmitriy1892.kmp.libs.Platform

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()