package ru.otus.algorithms.graph.dijkstra

data class VertexState(var weight: Int? = null, var processed: Boolean = false, var edge: Edge? = null)
