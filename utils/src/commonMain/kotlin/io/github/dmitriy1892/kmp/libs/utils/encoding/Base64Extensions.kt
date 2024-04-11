package io.github.dmitriy1892.kmp.libs.utils.encoding

fun String.fromBase64(ignoreSpaces: Boolean = false): ByteArray =
    Base64.decode(ignoreSpaces, this, false)

fun String.fromBase64UrlEncoded(ignoreSpaces: Boolean = false): ByteArray =
    Base64.decode(ignoreSpaces, this, true)

fun ByteArray.toBase64(): String =
    Base64.encode(src = this, url = false, doPadding = true)

fun ByteArray.toBase64UrlEncoded(doPadding: Boolean = false): String =
    Base64.encode(this, true, doPadding)