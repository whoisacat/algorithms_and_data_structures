package ru.otus.algorithms.dynamicarrays

import com.whoisacat.edu.otus.algoriths.dynamicarrays.DynamicArray
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SingleArrayTest {

    private fun createObject() = SingleArray<Int>()

    @Test
    fun addToEmpty() {
        val array: DynamicArray<Int> = createObject()
        array.add(7, 0)
        assertEquals(1, array.size())
    }

    @Test
    fun addToNonEmpty() {
        val array = createObject()
        array.add(7, 0)
        array.add(8, 1)
        assertEquals(2, array.size())
    }

    @Test
    fun addInTheMiddle() {
        val array = createObject()
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
    fun addAtTheStart() {
        val array = createObject()
        array.add(7, 0)
        assertEquals(1, array.size())
        array.add(8, 1)
        assertEquals(2, array.size())
        array.add(9, 0)
        assertEquals(3, array.size())
    }

    @Test
    fun deleteFromTheStart() {
        val array = createObject()
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
    fun deleteFromTheTail() {
        val array = createObject()
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
    fun deleteFromTheMiddle() {
        val array = createObject()
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