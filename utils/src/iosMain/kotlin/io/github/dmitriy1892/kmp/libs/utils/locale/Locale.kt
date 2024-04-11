package io.github.dmitriy1892.kmp.libs.utils.locale

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

actual object Locale {

    actual fun getLanguageCode(): String = NSLocale.currentLocale.languageCode.lowercase()
}