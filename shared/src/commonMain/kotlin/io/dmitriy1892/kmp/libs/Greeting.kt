package io.dmitriy1892.kmp.libs

class Greeting {
    private val platform: Platform = io.dmitriy1892.kmp.libs.getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}