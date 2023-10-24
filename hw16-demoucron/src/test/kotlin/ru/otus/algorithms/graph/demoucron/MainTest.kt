package ru.otus.algorithms.graph.demoucron

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.lang.Exception

class MainKtTest {

    @Test
    fun cycleTest() {
        val v0: Array<Int> = arrayOf(1)
        val v1: Array<Int> = arrayOf(2, 4, 5)
        val v2: Array<Int> = arrayOf(3, 6)
        val v3: Array<Int> = arrayOf(2, 7)
        val v4: Array<Int> = arrayOf(0, 5)
        val v5: Array<Int> = arrayOf(6)
        val v6: Array<Int> = arrayOf(5)
        val v7: Array<Int> = arrayOf(3, 6)
        val vectors: Array<Array<Int>> = arrayOf(v0, v1, v2, v3, v4, v5, v6, v7)
        val throwable = assertThrows(Exception::class.java,
            { sort(vectors) },
            "topological sorting is not possible in graphs with a cycle")

        assertEquals("topological sorting is not possible in graphs with a cycle", throwable.message)
    }

    @Test
    fun successTest() {
        val vectors: Array<Array<Int>> = arrayOf(
            arrayOf(9), arrayOf(2), arrayOf(7, 10), arrayOf(1, 2, 7, 9),
            arrayOf(0, 3), arrayOf(2), arrayOf(8), arrayOf(6, 8), arrayOf(11),
            arrayOf(7, 11), arrayOf(), arrayOf())
        val levels = sort(vectors)

        val expected = arrayOf(
            arrayOf(0, 1, 2, 3, 4, 5, 6, 7), arrayOf(4, 5), arrayOf(0, 3), arrayOf(1, 9),
            arrayOf(2), arrayOf(7, 10), arrayOf(6), arrayOf(8), arrayOf(11)
        )
        assertArrayEquals(expected, levels)
    }
}