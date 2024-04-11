package io.dmitriy1892.kmp.libs

import io.dmitriy1892.kmp.libs.Platform
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = "CUSTOM_TEXT_234 " +
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()