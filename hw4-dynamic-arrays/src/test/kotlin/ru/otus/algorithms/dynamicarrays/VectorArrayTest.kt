package ru.otus.algorithms.dynamicarrays

import com.whoisacat.edu.otus.algoriths.dynamicarrays.DynamicArray
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class VectorArrayTest {

    private fun createObjectVector32() = VectorArray<Int>(32)
    private fun createObjectVector2() = VectorArray<Int>(2)

    @Test
    fun addToEmptyVector2() {
        val array: DynamicArray<Int> = createObjectVector2()
        array.add(7, 0)
        assertEquals(1, array.size())
    }

    @Test
    fun addToNonEmptyVector2() {
        val array = createObjectVector2()
        array.add(7, 0)
        array.add(8, 1)
        assertEquals(2, array.size())
    }

    @Test
    fun addInTheMiddleVector2() {
        val array = createObjectVector2()
        array.add(7, 0)
        assertEquals(1, array.size())
        array.add(8, 1)
        assertEquals(2, array.size())
        array.add(9, 1)
        assertEquals(3, array.size())
        array.add(10, 2)
        assertEquals(4, array.size())
        array.add(11, 1)
        assertEquals(5, array.size())
    }

    @Test
    fun addAtTheStartVector2() {
        val array = createObjectVector2()
        array.add(7, 0)
        assertEquals(1, array.size())
        array.add(8, 1)
        assertEquals(2, array.size())
        array.add(9, 0)
        assertEquals(3, array.size())
    }

    @Test
    fun deleteFromTheStartVector2() {
        val array = createObjectVector2()
        array.add(7, 0)
        assertEquals(1, array.size())
        array.add(8, 1)
        assertEquals(2, array.size())
        array.add(9, 0)
        assertEquals(3, array.size())
        array.add(10, 2)
        assertEquals(4, array.size())
        assertEquals(9, array.remove(0))
        assertEquals(3, array.size())
    }

    @Test
    fun deleteFromTheTailVector2() {
        val array = createObjectVector2()
        array.add(7, 0)
        assertEquals(1, array.size())
        array.add(8, 1)
        assertEquals(2, array.size())
        array.add(9, 0)
        assertEquals(3, array.size())
        array.add(10, 2)
        assertEquals(4, array.size())
        assertEquals(8, array.remove(3))
        assertEquals(3, array.size())
    }

    @Test
    fun deleteFromTheMiddleVector2() {
        val array = createObjectVector2()
        array.add(7, 0)
        assertEquals(1, array.size())
        array.add(8, 1)
        assertEquals(2, array.size())
        array.add(9, 0)
        assertEquals(3, array.size())
        array.add(10, 2)
        array.add(10, 1)
        assertEquals(5, array.size())
        assertEquals(7, array.remove(2))
        assertEquals(4, array.size())
    }

    @Test
    fun addToEmptyVector32() {
        val array: DynamicArray<Int> = createObjectVector32()
        array.add(7, 0)
        assertEquals(1, array.size())
    }

    @Test
    fun addToNonEmptyVector32() {
        val array = createObjectVector2()
        array.add(7, 0)
        array.add(8, 1)
        assertEquals(2, array.size())
    }

    @Test
    fun addInTheMiddleVector32() {
        val array = createObjectVector32()
        array.add(7, 0)
        assertEquals(1, array.size())
        array.add(8, 1)
        assertEquals(2, array.size())
        array.add(9, 1)
        assertEquals(3, array.size())
        array.add(10, 2)
        assertEquals(4, array.size())
        array.add(11, 1)
        assertEquals(5, array.size())
    }

    @Test
    fun addAtTheStartVector32() {
        val array = createObjectVector32()
        array.add(7, 0)
        assertEquals(1, array.size())
        array.add(8, 1)
        assertEquals(2, array.size())
        array.add(9, 0)
        assertEquals(3, array.size())
    }

    @Test
    fun deleteFromTheStartVector32() {
        val array = createObjectVector32()
        array.add(7, 0)
        assertEquals(1, array.size())
        array.add(8, 1)
        assertEquals(2, array.size())
        array.add(9, 0)
        assertEquals(3, array.size())
        array.add(10, 2)
        assertEquals(4, array.size())
        assertEquals(9, array.remove(0))
        assertEquals(3, array.size())
    }

    @Test
    fun deleteFromTheTailVector32() {
        val array = createObjectVector32()
        array.add(7, 0)
        assertEquals(1, array.size())
        array.add(8, 1)
        assertEquals(2, array.size())
        array.add(9, 0)
        assertEquals(3, array.size())
        array.add(10, 2)
        assertEquals(4, array.size())
        assertEquals(8, array.remove(3))
        assertEquals(3, array.size())
    }

    @Test
    fun deleteFromTheMiddleVector32() {
        val array = createObjectVector32()
        array.add(7, 0)
        assertEquals(1, array.size())
        array.add(8, 1)
        assertEquals(2, array.size())
        array.add(9, 0)
        assertEquals(3, array.size())
        array.add(10, 2)
        array.add(10, 1)
        assertEquals(5, array.size())
        assertEquals(7, array.remove(2))
        assertEquals(4, array.size())
    }
}