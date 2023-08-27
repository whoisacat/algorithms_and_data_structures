package ru.otus.algorithms.dynamicarrays

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ArrayPriorityQueueTest {

    @Test
    fun directTest() {
        val queue = ArrayPriorityQueue<String>()

        queue.enqueue(0, "01")
        queue.enqueue(3, "31")
        queue.enqueue(1, "11")
        queue.enqueue(0, "02")
        queue.enqueue(0, "03")
        queue.enqueue(1, "12")
        assertEquals("01", queue.dequeue())
        assertEquals("02", queue.dequeue())
        assertEquals("03", queue.dequeue())
        assertEquals("11", queue.dequeue())
        assertEquals("12", queue.dequeue())
        assertEquals("31", queue.dequeue())
    }

    @Test
    fun revertTest() {
        val queue = ArrayPriorityQueue<String>()

        queue.enqueue(3, "31")
        queue.enqueue(1, "11")
        queue.enqueue(0, "01")
        queue.enqueue(0, "02")
        queue.enqueue(0, "03")
        queue.enqueue(1, "12")
        assertEquals("01", queue.dequeue())
        assertEquals("02", queue.dequeue())
        assertEquals("03", queue.dequeue())
        assertEquals("11", queue.dequeue())
        assertEquals("12", queue.dequeue())
        assertEquals("31", queue.dequeue())
    }
}