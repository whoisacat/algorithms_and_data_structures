package ru.otus.algorithms.work.course.hash

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import ru.otus.algorithms.work.course.domain.hash.QueuedLRUCache

class QueuedLRUCacheTest {

    @Test
    fun putSungle() {
        val cache = QueuedLRUCache<Int, Int>()
        cache.cache(1, 1)

        assertEquals(1, cache.firstNode!!.value)
        assertEquals(1, cache.firstNode!!.key)
        assertEquals(1, cache.lastNode!!.value)
        assertEquals(1, cache.lastNode!!.key)
    }

    @Test
    fun putTwo() {
        val cache = QueuedLRUCache<Int, Int>()
        cache.cache(1, 1)
        cache.cache(2, 2)

        assertEquals(2, cache.firstNode!!.value)
        assertEquals(2, cache.firstNode!!.key)
        assertEquals(1, cache.lastNode!!.value)
        assertEquals(1, cache.lastNode!!.key)
    }

    @Test
    fun putThree() {
        val cache = QueuedLRUCache<Int, Int>()
        cache.cache(1, 1)
        cache.cache(3, 3)
        cache.cache(2, 2)

        assertEquals(2, cache.firstNode!!.value)
        assertEquals(2, cache.firstNode!!.key)
        assertEquals(1, cache.lastNode!!.value)
        assertEquals(1, cache.lastNode!!.key)
    }

    @Test
    fun putFour() {
        val cache = QueuedLRUCache<Int, Int>()
        cache.cache(1, 1)
        cache.cache(3, 3)
        cache.cache(4, 46)
        cache.cache(2, 2)

        assertEquals(2, cache.firstNode!!.value)
        assertEquals(2, cache.firstNode!!.key)
        assertEquals(1, cache.lastNode!!.value)
        assertEquals(1, cache.lastNode!!.key)
    }

    @Test
    fun putFive() {
        val cache = QueuedLRUCache<Int, Int>()
        cache.cache(1, 1)
        cache.cache(3, 3)
        cache.cache(4, 46)
        cache.cache(5, 55)
        cache.cache(2, 2)

        assertEquals(2, cache.firstNode!!.value)
        assertEquals(2, cache.firstNode!!.key)
        assertEquals(1, cache.lastNode!!.value)
        assertEquals(1, cache.lastNode!!.key)
    }

    @Test
    fun putTwelve() {
        val cache = QueuedLRUCache<Int, Int>()
        cache.cache(1, 1)
        cache.cache(3, 3)
        cache.cache(11, 21)
        cache.cache(111, 211)
        cache.cache(2, 12)
        cache.cache(4, 14)
        cache.cache(6, 16)
        cache.cache(1, 3)
        cache.cache(5, 15)
        cache.cache(7, 17)
        cache.cache(8, 18)
        cache.cache(12, 22)
        cache.cache(9, 19)
        cache.cache(10, 20)

        assertEquals(20, cache.firstNode!!.value)
        assertEquals(10, cache.firstNode!!.key)
        assertEquals(12, cache.lastNode!!.value)
        assertEquals(2, cache.lastNode!!.key)

        assertEquals(3, cache.find(1))


        assertEquals(3, cache.firstNode!!.value)
        assertEquals(1, cache.firstNode!!.key)
        assertEquals(12, cache.lastNode!!.value)
        assertEquals(2, cache.lastNode!!.key)
    }

    @Test
    fun putTwelve2() {
        val cache = QueuedLRUCache<Int, Int>()
        cache.cache(1, 1)
        cache.cache(3, 3)
        cache.cache(11, 21)
        cache.cache(111, 211)
        cache.cache(2, 12)
        cache.cache(4, 14)
        cache.cache(6, 16)
        cache.cache(1, 3)
        cache.cache(5, 15)
        cache.cache(7, 17)
        cache.cache(8, 18)
        cache.cache(12, 22)
        cache.cache(9, 19)
        cache.cache(10, 20)
        cache.cache(10, 20)

        assertEquals(20, cache.firstNode!!.value)
        assertEquals(10, cache.firstNode!!.key)
        assertEquals(12, cache.lastNode!!.value)
        assertEquals(2, cache.lastNode!!.key)

        assertEquals(null, cache.find(3))
        assertEquals(null, cache.find(11))
        assertEquals(null, cache.find(111))
    }

    @Test
    fun find() {
        val cache = QueuedLRUCache<Int, Int>()
        cache.cache(1, 1)
        cache.cache(3, 7)
        cache.cache(2, 2)

        assertEquals(7, cache.find(3))
        assertEquals(7, cache.firstNode!!.value)
        assertEquals(3, cache.firstNode!!.key)
        assertEquals(1, cache.lastNode!!.value)
        assertEquals(1, cache.lastNode!!.key)
    }
}