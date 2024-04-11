package io.github.dmitriy1892.kmp.libs.utils.locale

/**
 * Class for platform locale access.
 */
expect object Locale {

    /**
     * Get language code in format "en", "ru" etc.
     *
     * @return [String] - locale string.
     */
    fun getLanguageCode(): String
}