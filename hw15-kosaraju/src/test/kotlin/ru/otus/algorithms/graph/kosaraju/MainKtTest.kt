package ru.otus.algorithms.graph.kosaraju

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.Arrays

class MainKtTest {

    @Test
    fun main() {
        val v0: Array<Int?> = arrayOf(1)
        val v1: Array<Int?> = arrayOf(2, 4, 5)
        val v2: Array<Int?> = arrayOf(3, 6)
        val v3: Array<Int?> = arrayOf(2, 7)
        val v4: Array<Int?> = arrayOf(0, 5)
        val v5: Array<Int?> = arrayOf(6)
        val v6: Array<Int?> = arrayOf(5)
        val v7: Array<Int?> = arrayOf(3, 6)
        val vectors: Array<Array<Int?>> = arrayOf(v0, v1, v2, v3, v4, v5, v6, v7)
        val result = ru.otus.algorithms.graph.kosaraju.main(vectors)

        assertArrayEquals(arrayOf(6, 7, 1), result)
    }
}