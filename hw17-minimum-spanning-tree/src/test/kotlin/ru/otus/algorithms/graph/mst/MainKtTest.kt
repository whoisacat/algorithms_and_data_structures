package ru.otus.algorithms.graph.mst

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MainKtTest {

    @Test
    fun minimalSpanningTree() {
        val adjacencyVectors: Array<Array<Array<Int>>> = arrayOf(
            arrayOf(arrayOf(1, 4), arrayOf(4, 3), arrayOf(5, 3)),
            arrayOf(arrayOf(0, 4), arrayOf(2, 2), arrayOf(4, 1), arrayOf(7, 3)),
            arrayOf(arrayOf(1, 2), arrayOf(3, 3), arrayOf(6, 2)),
            arrayOf(arrayOf(2, 3), arrayOf(4, 4)),
            arrayOf(arrayOf(0, 3), arrayOf(1, 1), arrayOf(3, 4), arrayOf(3, 4), arrayOf(5, 4), arrayOf(6, 2),
                arrayOf(7, 2)),
            arrayOf(arrayOf(0, 3), arrayOf(4, 4), arrayOf(6, 3)),
            arrayOf(arrayOf(2, 2), arrayOf(4, 2), arrayOf(5, 3)),
            arrayOf(arrayOf(1, 3), arrayOf(4, 2))
        )

        val result = minimalSpanningTree(adjacencyVectors)

        assertEquals(adjacencyVectors.size - 1, result.size)
        val expected = arrayOf(Edge(v1=1, v2=4),
            Edge(v1=4, v2=7),
            Edge(v1=4, v2=6),
            Edge(v1=2, v2=6),
            Edge(v1=5, v2=6),
            Edge(v1=2, v2=3),
            Edge(v1=0, v2=5))
        assertArrayEquals(expected, result)
    }

    @Test
    fun getSortedEdgesArrayTest() {
        val adjacencyVectors: Array<Array<Array<Int>>> = arrayOf(
            arrayOf(arrayOf(1, 4), arrayOf(4, 3), arrayOf(5, 3)),
            arrayOf(arrayOf(0, 4), arrayOf(2, 2), arrayOf(4, 1), arrayOf(7, 3)),
            arrayOf(arrayOf(1, 2), arrayOf(3, 3), arrayOf(6, 2)),
            arrayOf(arrayOf(2, 3), arrayOf(4, 4)),
            arrayOf(arrayOf(0, 3), arrayOf(1, 1), arrayOf(3, 4), arrayOf(3, 4), arrayOf(5, 4), arrayOf(6, 2),
                arrayOf(7, 2)),
            arrayOf(arrayOf(0, 3), arrayOf(4, 4), arrayOf(6, 3)),
            arrayOf(arrayOf(2, 2), arrayOf(4, 2), arrayOf(5, 3)),
            arrayOf(arrayOf(1, 3), arrayOf(4, 2))
        )

        val sortedEdgesArray = getSortedEdgesArray(adjacencyVectors)
        val expected = arrayOf(arrayOf(1, 4, 1), arrayOf(4, 7, 2), arrayOf(4, 6, 2), arrayOf(2, 6, 2),
            arrayOf(1, 2, 2), arrayOf(5, 6, 3), arrayOf(2, 3, 3), arrayOf(1, 7, 3), arrayOf(0, 5, 3),
            arrayOf(0, 4, 3), arrayOf(4, 5, 4), arrayOf(3, 4, 4), arrayOf(0, 1, 4))
        assertArrayEquals(expected, sortedEdgesArray)
    }
}