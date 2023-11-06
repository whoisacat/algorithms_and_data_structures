package ru.otus.algorithms.graph.mst

import java.lang.Exception

data class Edge(val v1: Int, val v2: Int) {

    init {
        if ((v1 == Int.MAX_VALUE || v2 == Int.MAX_VALUE) && v1 != v2)
            throw Exception("max value vertex could be only in stub edge")
    }
    companion object {
        val stub: Edge = Edge(Int.MAX_VALUE, Int.MAX_VALUE)
    }
}