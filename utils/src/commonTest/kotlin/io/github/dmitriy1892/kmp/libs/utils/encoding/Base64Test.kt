package io.github.dmitriy1892.kmp.libs.utils.encoding

import io.github.dmitriy1892.kmp.libs.utils.encoding.Base64
import io.github.dmitriy1892.kmp.libs.utils.encoding.fromBase64
import io.github.dmitriy1892.kmp.libs.utils.encoding.fromBase64UrlEncoded
import io.github.dmitriy1892.kmp.libs.utils.encoding.toBase64
import io.github.dmitriy1892.kmp.libs.utils.encoding.toBase64UrlEncoded
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class Base64Test {

    @Test
    fun testRegex() {
        val regex = Regex("[\\s\\n\\r\\t]")
        val data = "  \n   some\tva\nlue     \n\r\t"

        val out = regex.replace(data, "")

        assertEquals("somevalue", out)
    }

    @Test
    fun decode() {
        val initial = "U29tZSB0ZXh0IHdpdGggbnVtYmVycyAxMjM0NTY3"

        val decoded = Base64.decode(initial).decodeToString()

        assertEquals("Some text with numbers 1234567", decoded)
    }

    @Test
    fun encode() {
        val initial = "Some text with numbers 1234567"

        val encoded = Base64.encode(initial.encodeToByteArray())

        assertEquals("U29tZSB0ZXh0IHdpdGggbnVtYmVycyAxMjM0NTY3", encoded)
    }

    @Test
    fun testMimeDecoder() {
        val initial = "U29tZSB0ZXN0IHN0cmluZyBmb3IgZW5jb2Rpbmc="

        val decoded = Base64.decode(initial).decodeToString()

        assertEquals("Some test string for encoding", decoded)
    }

    @Test
    fun testMimeEncoder() {
        val initial = "Some test string for encoding"

        val encoded = Base64.encode(initial.encodeToByteArray())

        assertEquals("U29tZSB0ZXN0IHN0cmluZyBmb3IgZW5jb2Rpbmc=", encoded)
    }

    //////////////////// Base64 test //////////////
    @Test
    fun byteArray_base64Decoded() {
        assertEquals("Hello, world!", "SGVsbG8sIHdvcmxkIQ==".fromBase64().decodeToString())
        assertContentEquals(
            byteArrayOf(
                -94, 124, -26, -112, -72, -84, 16, 11, 67, -45, 107, 38, -99, 79, 62, -49, 83, 26, -85, -70, -122, 53,
                67, 42, -94, -87, 61, -74, 66, 0, 80, -125, -17, -11, -125, 63, 109, -15, 56, -95, -33, 18, 110, 47,
                47, -20, -72, -34, 53, -69, 49, -45, 54, 53, -21, 43, 9, -84, -125, 72, -61, 76, 31, -46,
            ),
            "onzmkLisEAtD02smnU8+z1Maq7qGNUMqoqk9tkIAUIPv9YM/bfE4od8Sbi8v7LjeNbsx0zY16ysJrINIw0wf0g==".fromBase64(),
        )
    }

    @Test
    fun byteArray_base64Encoded() {
        assertEquals(
            "xvrp9DBWlei2mG0ov9MN+A==", // value1
            byteArrayOf(-58, -6, -23, -12, 48, 86, -107, -24, -74, -104, 109, 40, -65, -45, 13, -8).toBase64(),
        )
        assertEquals(
            "IkYJxF8nIQD9RY7Yk6r26A==", // value222
            byteArrayOf(34, 70, 9, -60, 95, 39, 33, 0, -3, 69, -114, -40, -109, -86, -10, -24).toBase64(),
        )
        assertEquals(
            "U0GeVBi2dNcdL2IO0nJo5Q==", // value555
            byteArrayOf(83, 65, -98, 84, 24, -74, 116, -41, 29, 47, 98, 14, -46, 114, 104, -27).toBase64(),
        )
    }

    @Test
    fun string_base64Decoded() {
        assertEquals("word", "d29yZA==".fromBase64().decodeToString())
        assertEquals("Word", "V29yZA==".fromBase64().decodeToString())
        assertEquals("Hello", "SGVsbG8=".fromBase64().decodeToString())
        assertEquals("World!", "V29ybGQh".fromBase64().decodeToString())
        assertEquals("Hello, world!", "SGVsbG8sIHdvcmxkIQ==".fromBase64().decodeToString())
        assertEquals(
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/",
            "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==".fromBase64().decodeToString(),
        )
        assertEquals("abcd", "YWJjZA==".fromBase64().decodeToString())
        assertEquals(
            "1234567890-=!@#\$%^&*()_+qwertyuiop[];'\\,./?><|\":}{P`~",
            "MTIzNDU2Nzg5MC09IUAjJCVeJiooKV8rcXdlcnR5dWlvcFtdOydcLC4vPz48fCI6fXtQYH4=".fromBase64().decodeToString(),
        )
        assertEquals("saschpe", "c2FzY2hwZQ==".fromBase64().decodeToString())
    }

    @Test
    fun string_base64Encoded() {
        assertEquals("d29yZA==", "word".encodeToByteArray().toBase64())
        assertEquals("V29yZA==", "Word".encodeToByteArray().toBase64())
        assertEquals("SGVsbG8=", "Hello".encodeToByteArray().toBase64())
        assertEquals("V29ybGQh", "World!".encodeToByteArray().toBase64())
        assertEquals("SGVsbG8sIHdvcmxkIQ==", "Hello, world!".encodeToByteArray().toBase64())
        assertEquals("SGVsbG8sIHdvcmxkIQ==", "Hello, world!".encodeToByteArray().toBase64())
        assertEquals(
            "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==",
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".encodeToByteArray().toBase64(),
        )
        assertEquals("YWJjZA==", "abcd".encodeToByteArray().toBase64())
        assertEquals(
            "MTIzNDU2Nzg5MC09IUAjJCVeJiooKV8rcXdlcnR5dWlvcFtdOydcLC4vPz48fCI6fXtQYH4=",
            "1234567890-=!@#\$%^&*()_+qwertyuiop[];'\\,./?><|\":}{P`~".encodeToByteArray().toBase64(),
        )
        assertEquals("c2FzY2hwZQ==", "saschpe".encodeToByteArray().toBase64())
    }

    @Test
    fun string_roundTripEncoder() =
        assertEquals(
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/",
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".encodeToByteArray().toBase64().fromBase64().decodeToString()
        )

    //////////////////// URL ENCODER TEST ////////////////

    @Test
    fun byteArray_base64UrlDecoded() {
        assertEquals("Hello, world!", "SGVsbG8sIHdvcmxkIQ==".fromBase64UrlEncoded().decodeToString())
        assertContentEquals(
            byteArrayOf(
                -94, 124, -26, -112, -72, -84, 16, 11, 67, -45, 107, 38, -99, 79, 62, -49, 83, 26, -85, -70, -122, 53,
                67, 42, -94, -87, 61, -74, 66, 0, 80, -125, -17, -11, -125, 63, 109, -15, 56, -95, -33, 18, 110, 47,
                47, -20, -72, -34, 53, -69, 49, -45, 54, 53, -21, 43, 9, -84, -125, 72, -61, 76, 31, -46,
            ),
            "onzmkLisEAtD02smnU8-z1Maq7qGNUMqoqk9tkIAUIPv9YM_bfE4od8Sbi8v7LjeNbsx0zY16ysJrINIw0wf0g==".fromBase64UrlEncoded(),
        )
    }

    @Test
    fun byteArray_base64UrlEncoded() {
        assertEquals(
            "xvrp9DBWlei2mG0ov9MN-A", // value1
            byteArrayOf(-58, -6, -23, -12, 48, 86, -107, -24, -74, -104, 109, 40, -65, -45, 13, -8).toBase64UrlEncoded(),
        )
        assertEquals(
            "IkYJxF8nIQD9RY7Yk6r26A", // value222
            byteArrayOf(34, 70, 9, -60, 95, 39, 33, 0, -3, 69, -114, -40, -109, -86, -10, -24).toBase64UrlEncoded(),
        )
        assertEquals(
            "U0GeVBi2dNcdL2IO0nJo5Q", // value555
            byteArrayOf(83, 65, -98, 84, 24, -74, 116, -41, 29, 47, 98, 14, -46, 114, 104, -27).toBase64UrlEncoded(),
        )
    }

    @Test
    fun string_base64UrlDecoded() {
        assertEquals("word", "d29yZA==".fromBase64UrlEncoded().decodeToString())
        assertEquals("Word", "V29yZA==".fromBase64UrlEncoded().decodeToString())
        assertEquals("Hello", "SGVsbG8=".fromBase64UrlEncoded().decodeToString())
        assertEquals("World!", "V29ybGQh".fromBase64UrlEncoded().decodeToString())
        assertEquals("Hello, world!", "SGVsbG8sIHdvcmxkIQ".fromBase64UrlEncoded().decodeToString())
        assertEquals("Hello, world!", "SGVsbG8sIHdvcmxkIQ==".fromBase64UrlEncoded().decodeToString())
        assertEquals(
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/",
            "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==".fromBase64UrlEncoded().decodeToString(),
        )
        assertEquals("Salt", "U2FsdA==".fromBase64UrlEncoded().decodeToString())
        assertEquals("Pepper", "UGVwcGVy".fromBase64UrlEncoded().decodeToString())
        assertEquals("abcd", "YWJjZA".fromBase64UrlEncoded().decodeToString())
        assertEquals(
            "1234567890-=!@#\$%^&*()_+qwertyuiop[];'\\,./?><|\":}{P`~",
            "MTIzNDU2Nzg5MC09IUAjJCVeJiooKV8rcXdlcnR5dWlvcFtdOydcLC4vPz48fCI6fXtQYH4".fromBase64UrlEncoded().decodeToString(),
        )
        assertEquals("saschpe", "c2FzY2hwZQ".fromBase64UrlEncoded().decodeToString())
    }

    @Test
    fun string_base64UrlEncoded() {
        assertEquals("d29yZA", "word".encodeToByteArray().toBase64UrlEncoded())
        assertEquals("V29yZA", "Word".encodeToByteArray().toBase64UrlEncoded())
        assertEquals("SGVsbG8", "Hello".encodeToByteArray().toBase64UrlEncoded())
        assertEquals("V29ybGQh", "World!".encodeToByteArray().toBase64UrlEncoded())
        assertEquals("SGVsbG8sIHdvcmxkIQ", "Hello, world!".encodeToByteArray().toBase64UrlEncoded())
        assertEquals("SGVsbG8sIHdvcmxkIQ", "Hello, world!".encodeToByteArray().toBase64UrlEncoded())
        assertEquals(
            "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw",
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".encodeToByteArray().toBase64UrlEncoded(),
        )
        assertEquals("U2FsdA", "Salt".encodeToByteArray().toBase64UrlEncoded())
        assertEquals("UGVwcGVy", "Pepper".encodeToByteArray().toBase64UrlEncoded())
        assertEquals("YWJjZA", "abcd".encodeToByteArray().toBase64UrlEncoded())
        assertEquals(
            "MTIzNDU2Nzg5MC09IUAjJCVeJiooKV8rcXdlcnR5dWlvcFtdOydcLC4vPz48fCI6fXtQYH4",
            "1234567890-=!@#\$%^&*()_+qwertyuiop[];'\\,./?><|\":}{P`~".encodeToByteArray().toBase64UrlEncoded(),
        )
        assertEquals("c2FzY2hwZQ", "saschpe".encodeToByteArray().toBase64UrlEncoded())
    }

    @Test
    fun string_roundTripUrlEncoder() =
        assertEquals(
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_",
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".encodeToByteArray().toBase64UrlEncoded().fromBase64UrlEncoded().decodeToString()
        )
}