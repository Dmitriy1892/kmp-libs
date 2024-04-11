package io.github.dmitriy1892.kmp.libs.utils.platform

object Config {
    val isDebugBuild: Boolean = isDebug()
}

internal expect fun isDebug(): Boolean