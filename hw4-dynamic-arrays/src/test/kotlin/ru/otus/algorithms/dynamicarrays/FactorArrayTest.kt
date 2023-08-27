package ru.otus.algorithms.dynamicarrays

import com.whoisacat.edu.otus.algoriths.dynamicarrays.DynamicArray
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FactorArrayTest {

    private fun createObject() = FactorArray<Int>(2)

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
        array.add(10, 1)
        assertEquals(6, array.size())
        assertEquals(10, array.remove(2))
        assertEquals(5, array.size())
    }

    @Test
    fun deleteFromTheMiddleCloserToEnd() {
        val array = createObject()
        array.add(7, 0)// 0 0 1 1 2 3
        assertEquals(1, array.size())
        array.add(8, 1)// 1 2 3 4 5
        assertEquals(2, array.size())
        array.add(9, 0)// 0 0 0 0
        assertEquals(3, array.size())
        array.add(13, 2)// 2 3 4
        array.add(11, 1)// 1 2
        array.add(10, 1)// 1
        assertEquals(6, array.size())
        assertEquals(13, array.remove(4))
        assertEquals(5, array.size())//exp //9 10 11 7 13 8 //act//9 10 11 11 7 13
    }
}