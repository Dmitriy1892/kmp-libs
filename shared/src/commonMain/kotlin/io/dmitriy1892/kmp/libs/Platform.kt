package io.dmitriy1892.kmp.libs

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform