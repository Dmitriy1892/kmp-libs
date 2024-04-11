package io.github.dmitriy1892.kmp.libs.utils.uuid

import platform.Foundation.NSUUID

actual object UUID {

    actual fun randomString(): String = NSUUID.UUID().UUIDString

    actual fun randomNotEquals(uuids: Set<String>): String {
        var random = randomString()

        while (uuids.contains(random)) {
            random = randomString()
        }

        return random
    }
}