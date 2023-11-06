package ru.otus.algorithms.graph.dijkstra

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MainTest {

    @Test
    fun dijkstraTest() {
        val adjacencyVectors: Array<Array<Array<Int>>> = arrayOf(
            arrayOf(arrayOf(1, 1), arrayOf(4, 3), arrayOf(5, 1)),
            arrayOf(arrayOf(0, 1), arrayOf(2, 2), arrayOf(4, 2)),
            arrayOf(arrayOf(1, 2), arrayOf(3, 4), arrayOf(4, 1)),
            arrayOf(arrayOf(2, 4), arrayOf(4, 3), arrayOf(6, 1)),
            arrayOf(arrayOf(0, 3), arrayOf(1, 2), arrayOf(2, 1), arrayOf(3, 3), arrayOf(5, 2), arrayOf(6, 4)),
            arrayOf(arrayOf(0, 1), arrayOf(4, 2), arrayOf(6, 3)),
            arrayOf(arrayOf(3, 1), arrayOf(4, 4), arrayOf(5, 3))
        )

        val result = dijkstra(adjacencyVectors, 0)

        assertEquals(adjacencyVectors.size - 1, result.size)
        val expected =
            arrayOf(
                Edge(v1 = 0, v2 = 1),
                Edge(v1 = 1, v2 = 2),
                Edge(v1 = 6, v2 = 3),
                Edge(v1 = 0, v2 = 4),
                Edge(v1 = 0, v2 = 5),
                Edge(v1 = 5, v2 = 6))
        assertArrayEquals(expected, result)
    }

    @Test
    fun dijkstraWithDestinationTest() {
        val adjacencyVectors: Array<Array<Array<Int>>> = arrayOf(
            arrayOf(arrayOf(1, 1), arrayOf(4, 3), arrayOf(5, 1)),
            arrayOf(arrayOf(0, 1), arrayOf(2, 2), arrayOf(4, 2)),
            arrayOf(arrayOf(1, 2), arrayOf(3, 4), arrayOf(4, 1)),
            arrayOf(arrayOf(2, 4), arrayOf(4, 3), arrayOf(6, 1)),
            arrayOf(arrayOf(0, 3), arrayOf(1, 2), arrayOf(2, 1), arrayOf(3, 3), arrayOf(5, 2), arrayOf(6, 4)),
            arrayOf(arrayOf(0, 1), arrayOf(4, 2), arrayOf(6, 3)),
            arrayOf(arrayOf(3, 1), arrayOf(4, 4), arrayOf(5, 3))
        )

        val result = dijkstra(adjacencyVectors, 0, 5)

        assertEquals(adjacencyVectors.size - 2, result.size)
        val expected =
            arrayOf(
                Edge(v1 = 0, v2 = 1),
                Edge(v1 = 1, v2 = 2),
                Edge(v1 = 0, v2 = 4),
                Edge(v1 = 0, v2 = 5),
                Edge(v1 = 5, v2 = 6)
                )
        assertArrayEquals(expected, result)
    }

    @Test
    fun dijkstra2VerticesTest() {
        val adjacencyVectors: Array<Array<Array<Int>>> = arrayOf(
            arrayOf(arrayOf(1, 1)),
            arrayOf(arrayOf(0, 1)))

        val result = dijkstra(adjacencyVectors, 0)

        assertEquals(adjacencyVectors.size - 1, result.size)
        val expected = arrayOf(Edge(v1 = 0, v2 = 1))
        assertArrayEquals(expected, result)
    }

    @Test
    fun collectEdgesFirstNullTest() {
        val edges = collectEdges(arrayOf(VertexState(0, true, null),
            VertexState(0, true, Edge(1, 2)),
            VertexState(0, true, Edge(2, 3)),
            VertexState(0, true, Edge(4, 5))))
        val expected = arrayOf(Edge(v1 = 1, v2 = 2), Edge(v1 = 2, v2 = 3), Edge(v1 = 4, v2 = 5))
        assertArrayEquals(expected, edges)
    }

    @Test
    fun collectEdgesMiddleNullTest() {
        val edges = collectEdges(arrayOf(VertexState(0, true, Edge(1, 2)),
            VertexState(0, true, null),
            VertexState(0, true, Edge(2, 3)),
            VertexState(0, true, Edge(4, 5))))
        val expected = arrayOf(Edge(v1 = 1, v2 = 2), Edge(v1 = 2, v2 = 3), Edge(v1 = 4, v2 = 5))
        assertArrayEquals(expected, edges)
    }

    @Test
    fun collectEdgesLastNullTest() {
        val edges = collectEdges(arrayOf(VertexState(0, true, Edge(1, 2)),
            VertexState(0, true, Edge(2, 3)),
            VertexState(0, true, Edge(4, 5)),
            VertexState(0, true, null)))
        val expected = arrayOf(Edge(v1 = 1, v2 = 2), Edge(v1 = 2, v2 = 3), Edge(v1 = 4, v2 = 5))
        assertArrayEquals(expected, edges)
    }
}