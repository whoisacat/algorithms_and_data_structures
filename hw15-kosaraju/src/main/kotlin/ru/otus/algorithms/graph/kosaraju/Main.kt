package ru.otus.algorithms.graph.kosaraju

import java.lang.Exception

fun main(adjacencyVectors: Array<Array<Int?>>): Array<Int?> {

    val inverted = invertedVertices(adjacencyVectors)

    val route = Route(Array(adjacencyVectors.size) { null })
    var result: Array<Int?> = Array(0) {null}
    for (idx in inverted) {
        if (route.route.contains(idx)) continue
        kosarajuDfs(adjacencyVectors, idx, route)
        val copy: Array<Int?> = Array(result.size + 1) {null}
        System.arraycopy(result, 0, copy, 0, result.size)
        for (v in route.route.indices) {
            if (route.route[v] == null) {
                copy[copy.size - 1] = route.route[v - 1]
                break
            }
        }
        result = copy
    }
    result[result.size - 1] = route.route[route.route.size - 1]
    return result
}

private fun invertedVertices(adjacencyVectors: Array<Array<Int?>>): Array<Int> {
    val copy = copyWithInvertingEdges(adjacencyVectors)
    val route = Route(Array(adjacencyVectors.size) { null })

    for (vertex in adjacencyVectors.indices) {
        if (route.route.contains(vertex)) continue
        route.route[route.routePointer++] = vertex
        kosarajuDfs(copy, vertex, route)
    }
    val inverted = Array<Int>(copy.size) {0}
    val reversedArray = route.route.reversedArray()
    for (idx in reversedArray.indices) {
        if (reversedArray[idx] == null) throw Exception("can not be null")
        inverted[idx] = reversedArray[idx]!!
    }
    return inverted
}

private fun kosarajuDfs(copy: Array<Array<Int?>>, copyIdx: Int, route: Route) {
    for (vertex in copy[copyIdx]) {
        if (vertex == null) continue
        if (route.route.contains(vertex)) continue
        route.route[route.routePointer++] = vertex
        kosarajuDfs(copy, vertex, route)
    }
}

private data class Route(val route: Array<Int?>) {
    var routePointer: Int = 0
        set(value) {
            field = value
            if (field > route.size) throw Exception("route is out of its size")
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Route

        if (!route.contentEquals(other.route)) return false
        return routePointer == other.routePointer
    }

    override fun hashCode(): Int {
        var result = route.contentHashCode()
        result = 31 * result + routePointer
        return result
    }
}

private fun copyWithInvertingEdges(initial: Array<Array<Int?>>): Array<Array<Int?>> {
    val maxPow = calcInvertedMaxPow(initial)
    val copy: Array<Array<Int?>> = Array(initial.size) {Array(maxPow) {null} }
    for (idx in initial.indices) {
        for (vertex in initial[idx]) {
            if (vertex == null) continue
            addVertexToVector(copy[vertex], idx)
        }
    }
    return copy
}

private fun addVertexToVector(vector: Array<Int?>, vertex: Int) {
    for (idx in vector.indices) {
        if (vector[idx] == null) {
            vector[idx] = vertex
            return
        }
    }
}

private fun calcInvertedMaxPow(initial: Array<Array<Int?>>): Int {
    val pows = Array(initial.size) {0}
    for (vertexIdx in initial.indices) {
        for (vertex in initial[vertexIdx]) {
            if (vertex == null) continue
            pows[vertex]++
        }
    }
    var max = pows[0]
    for (powerIdx in 1 until pows.size)
        if (max < pows[powerIdx])
            max = pows[powerIdx]
    return max
}
