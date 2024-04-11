package io.github.dmitriy1892.kmp.libs.utils.mutex

import io.github.dmitriy1892.kmp.libs.utils.coroutines.mutex.MutexVariable
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class MutexVariableTest {

    @Test
    fun testNullableMutexThrowsException() = runTest {
        val value = MutexVariable<Int?>(null)

        assertFails { value.getNotNull() }
    }

    @Test
    fun testNullableMutexWithNotNullValueReturnValue() = runTest {
        val value = MutexVariable<Int?>(1)

        assertEquals(1, value.getNotNull())
    }

    @Test
    fun testGetOrPutReturnDefaultValue() = runTest {
        val value = MutexVariable<Int?>(null)
        val result = value.getOrPut { 2 }

        assertEquals(2, result)
    }

    @Test
    fun testGetOrPutReturnStoredValue() = runTest {
        val value = MutexVariable<Int?>(4)
        val result = value.getOrPut { 2 }

        assertEquals(4, result)
    }
}