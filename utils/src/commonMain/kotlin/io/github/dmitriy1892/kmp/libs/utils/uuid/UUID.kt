package io.github.dmitriy1892.kmp.libs.utils.uuid

/**
 * Class for generating an UUID strings.
 */
expect object UUID {

    /**
     * Function for generating a random UUID string.
     *
     * @return [String] in UUID format.
     */
    fun randomString(): String

    /**
     * Function for generating a random UUID string.
     * Return random UUID string that not equals passed [uuids]
     *
     * @param uuids [Set] of UUID's that will not equals a generated UUID that will return by function.
     * @return [String] - random UUID string that **NOT** equals all of [uuids].
     */
    fun randomNotEquals(uuids: Set<String>): String
}