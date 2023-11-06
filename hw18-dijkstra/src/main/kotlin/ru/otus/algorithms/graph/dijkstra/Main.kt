package ru.otus.algorithms.graph.dijkstra

import java.lang.Exception

fun dijkstra(adjacencyVectors: Array<Array<Array<Int>>>, source: Int, destination: Int? = null): Array<Edge> {

    val verticesState: Array<VertexState> = Array(adjacencyVectors.size) { VertexState() }

    verticesState[source].weight = 0
    process(adjacencyVectors, source, verticesState)

    while (isThereUnprocessedVertices(verticesState)) {
        if (destination != null && verticesState[destination].processed) return collectReducedEdges(verticesState)
        var source1 = takeLessWeightVertexNumber(verticesState)
        process(adjacencyVectors, source1, verticesState)
    }
    return collectEdges(verticesState)
}

fun isThereUnprocessedVertices(verticesState: Array<VertexState>): Boolean {
    var processed = true
    for (vertex in verticesState) {
        processed = processed && vertex.processed
    }
    return !processed
}

fun takeLessWeightVertexNumber(verticesState: Array<VertexState>): Int {
    var min: Int? = null
    for (idx in verticesState.indices) {
        if (verticesState[idx].processed || verticesState[idx].weight == null) continue
        if (min == null || verticesState[idx].weight!! < verticesState[min].weight!!) min = idx
    }
    if (min == null) throw Exception("there is no one unprocessed vertex with weight")
    return min
}

private fun process(
    adjacencyVectors: Array<Array<Array<Int>>>,
    source: Int,
    verticesState: Array<VertexState>
) {
    val vector = adjacencyVectors[source]
    for (vertex in vector) {
        if (verticesState[vertex[0]].processed) continue
        if (verticesState[vertex[0]].weight == null || verticesState[vertex[0]].weight!! > verticesState[source].weight!! + vertex[1]) {//todo
            verticesState[vertex[0]].weight = verticesState[source].weight!! + vertex[1]
            verticesState[vertex[0]].edge = Edge(v1 = source, v2 = vertex[0])
        }
    }
    verticesState[source].processed = true
}

fun collectEdges(front: Array<VertexState>): Array<Edge> {
    val edges: Array<Edge?> = Array(front.size - 1) {null}
    val iterator = front.iterator()
    return Array(edges.size) {iterator.next().edge ?: iterator.next().edge!!}
}

fun collectReducedEdges(verticesState: Array<VertexState>): Array<Edge> {
    var array: Array<Edge> = emptyArray()
    for (state in verticesState) {
        if (state.edge != null) {
            val copy: Array<Edge> = Array(array.size + 1) { state.edge!! }
            System.arraycopy(array, 0, copy, 0, array.size)
            array = copy
        }
    }
    return array
}
