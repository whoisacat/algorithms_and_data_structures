package ru.otus.algorithms.dynamicarrays

import com.whoisacat.edu.otus.algoriths.dynamicarrays.DynamicArray
import com.whoisacat.edu.otus.algoriths.dynamicarrays.MatrixArray
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MatrixArrayTest {

    private fun createShortMatrixObject() = MatrixArray<Int>(3)
    private fun createLongMatrixObject() = MatrixArray<Int>(32)

    @Test
    fun addToEmpty() {
        val array: DynamicArray<Int> = createShortMatrixObject()
        array.add(7, 0)
        assertEquals(1, array.size())
    }

    @Test
    fun addToNonEmpty() {
        val array = createShortMatrixObject()
        array.add(7, 0)
        array.add(8, 1)
        assertEquals(2, array.size())
    }

    @Test
    fun addInTheMiddle() {
        val array = createShortMatrixObject()
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
        val array = createShortMatrixObject()
        array.add(7, 0)
        assertEquals(1, array.size())
        array.add(8, 1)
        assertEquals(2, array.size())
        array.add(9, 0)
        assertEquals(3, array.size())
    }

    @Test
    fun deleteFromTheStart() {
        val array = createShortMatrixObject()
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
        val array = createShortMatrixObject()
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
        val array = createShortMatrixObject()
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
        val array = createShortMatrixObject()
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
        assertEquals(5, array.size())
    }

    @Test
    fun addToEmptyLong() {
        val array: DynamicArray<Int> = createLongMatrixObject()
        array.add(7, 0)
        assertEquals(1, array.size())
    }

    @Test
    fun addToNonEmptyLong() {
        val array = createLongMatrixObject()
        array.add(7, 0)
        array.add(8, 1)
        assertEquals(2, array.size())
    }

    @Test
    fun addInTheMiddleLong() {
        val array = createLongMatrixObject()
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
    fun addAtTheStartLong() {
        val array = createLongMatrixObject()
        array.add(7, 0)
        assertEquals(1, array.size())
        array.add(8, 1)
        assertEquals(2, array.size())
        array.add(9, 0)
        assertEquals(3, array.size())
    }

    @Test
    fun deleteFromTheStartLong() {
        val array = createLongMatrixObject()
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
    fun deleteFromTheTailLong() {
        val array = createLongMatrixObject()
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
    fun deleteFromTheMiddleLong() {
        val array = createLongMatrixObject()
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
    fun deleteFromTheMiddleCloserToEndLong() {
        val array = createLongMatrixObject()
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
        assertEquals(5, array.size())
    }
}