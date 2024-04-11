package io.github.dmitriy1892.kmp.libs.utils.locale

import java.util.Locale

actual object Locale {

    actual fun getLanguageCode(): String = Locale.getDefault().language.lowercase()
}