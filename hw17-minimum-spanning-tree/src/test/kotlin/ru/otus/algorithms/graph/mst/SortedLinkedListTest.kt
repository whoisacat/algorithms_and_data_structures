package ru.otus.algorithms.graph.mst

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SortedLinkedListTest {

    @Test
    fun suzeIsIncreasingAfterAdd() {
        val list: SortedLinkedList = SortedLinkedList()
        list.add(arrayOf(1, 2, 3))

        assertEquals(1, list.size)
    }

    @Test
    fun suzeIsIncreasingAfterSecondAdd() {
        val list: SortedLinkedList = SortedLinkedList()
        list.add(arrayOf(1, 2, 3))
        list.add(arrayOf(1, 3, 4))

        assertEquals(2, list.size)
    }

    @Test
    fun orderedAdditionTest() {
        val list: SortedLinkedList = SortedLinkedList()
        list.add(arrayOf(1, 2, 3))
        list.add(arrayOf(1, 3, 4))

        assertEquals(3, list.first!!.item[2])
        assertEquals(4, list.first!!.next!!.item[2])
    }

    @Test
    fun unorderedAdditionSecondTest() {
        val list: SortedLinkedList = SortedLinkedList()
        list.add(arrayOf(0, 1, 4))
        list.add(arrayOf(0, 4, 3))

        assertEquals(3, list.first!!.item[2])
        assertEquals(4, list.first!!.next!!.item[2])
    }

    @Test
    fun insertInTheMiddleTest() {
        val list: SortedLinkedList = SortedLinkedList()
        list.add(arrayOf(1, 2, 3))
        list.add(arrayOf(1, 3, 5))
        list.add(arrayOf(5, 3, 4))

        assertEquals(3, list.first!!.item[2])
        assertEquals(4, list.first!!.next!!.item[2])
        assertEquals(5, list.first!!.next!!.next!!.item[2])
    }

    @Test
    fun unorderedAdditionTest() {
        val list: SortedLinkedList = SortedLinkedList()
        list.add(arrayOf(1, 2, 5))
        list.add(arrayOf(1, 3, 4))

        assertEquals(4, list.first!!.item[2])
        assertEquals(5, list.first!!.next!!.item[2])
    }
}