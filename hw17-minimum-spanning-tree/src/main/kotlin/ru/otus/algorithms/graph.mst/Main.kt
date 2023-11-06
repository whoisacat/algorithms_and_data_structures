package ru.otus.algorithms.graph.mst

import ru.otus.algorithms.graph.mst.Constants.V1_IDX
import ru.otus.algorithms.graph.mst.Constants.V2_IDX
import java.lang.Exception

object Constants {
    const val V1_IDX = 0
    const val V2_IDX = 1
}
fun minimalSpanningTree(adjacencyVectors: Array<Array<Array<Int>>>): Array<Edge> {

    val vertices = getVerticesArray(adjacencyVectors)
    val edges: Array<Array<Int>?> = getSortedEdgesArray(adjacencyVectors)

    var result = emptyArray<Edge>()
    for (idx in edges.indices) {
        if (result.size >= adjacencyVectors.size - 1) return result
        if (edges[idx] == null) continue
        if (!containsCycle(vertices, result, edges[idx]!!)) {
            result = addEdge(result, Edge(edges[idx]!![V1_IDX], edges[idx]!![V2_IDX]))
            edges[idx] = null
        }
    }
    return result
}

fun containsCycle(vertices: Array<Int>, edges: Array<Edge>, newEdge: Array<Int>): Boolean {
    val disjointSetsArray: Array<Int> = buildGraph(vertices, edges)
    return disjointSetsArray[newEdge[0]] == disjointSetsArray[newEdge[1]]
}

fun buildGraph(vertices: Array<Int>, edges: Array<Edge>): Array<Int> {
    val disjointSetsArray: Array<Int> = vertices.clone()
    for (edge in edges) {
        if (disjointSetsArray[edge.v2] == edge.v2) {
            disjointSetsArray[edge.v2] = disjointSetsArray[edge.v1]
        } else if (disjointSetsArray[edge.v1] == edge.v1) {
            disjointSetsArray[edge.v1] = disjointSetsArray[edge.v2]
        } else {
            throw Exception("unpredicted behavior")
        }
    }
    return disjointSetsArray
}

fun addEdge(arrayOfEdges: Array<Edge>, edge: Edge): Array<Edge> {
    val new: Array<Edge> = Array(arrayOfEdges.size + 1) {Edge.stub}
    System.arraycopy(arrayOfEdges, 0, new, 0, arrayOfEdges.size)
    new[new.size - 1] = edge
    return new
}

fun getSortedEdgesArray(adjacencyVectors: Array<Array<Array<Int>>>): Array<Array<Int>?> {
    val list = SortedLinkedList()
    for (vectorIdx in adjacencyVectors.indices) {
        for (adjacency in adjacencyVectors[vectorIdx]) {
            if (adjacency[0] >= vectorIdx) {
                list.add(arrayOf(vectorIdx, adjacency[0], adjacency[1]))
            }
        }
    }
    val iterator = list.iterator()
    return Array(list.size) { iterator.next()?.item }
}

fun getVerticesArray(adjacencyVectors: Array<Array<Array<Int>>>): Array<Int> {
    val iterator = adjacencyVectors.indices.iterator()
    return Array(adjacencyVectors.size) { iterator.next() }
}
