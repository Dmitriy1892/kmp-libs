package io.github.dmitriy1892.kmp.libs.utils.uuid

import java.util.UUID

actual object UUID {

    actual fun randomString(): String = UUID.randomUUID().toString()

    actual fun randomNotEquals(uuids: Set<String>): String {
        var random = randomString()

        while (uuids.contains(random)) {
            random = randomString()
        }

        return random
    }
}