package ru.otus.algorithms.graph.demoucron

import java.lang.Exception

fun sort(adjacencyVectors: Array<Array<Int>>): Array<Array<Int>> {

    val adjacencyMatrix = getAdjacencyMatrix(adjacencyVectors)
    val vectorOfPowersOfSemiSource = getVectorOfPowersOfSemiSource(adjacencyMatrix)

    val levels: Levels = Levels(emptyArray())
    while (vectorIsNotEmty(vectorOfPowersOfSemiSource)) {
        addLevel(levels, vectorOfPowersOfSemiSource, adjacencyMatrix)
    }
    return levels.toReturnArray()
}

private fun addLevel(levels: Levels, powers: Array<Int?>, matrix: Array<Array<Int>>) {
    var zeroPowers = emptyArray<Int>()
    for (idx in powers.indices) {
        if (powers[idx] == 0) {
            val copy: Array<Int> = Array(zeroPowers.size + 1) {Int.MAX_VALUE}
            System.arraycopy(zeroPowers, 0, copy, 0, zeroPowers.size)
            copy[zeroPowers.size] = idx
            zeroPowers = copy
        }
    }
    if (zeroPowers.isEmpty()) throw Exception("topological sorting is not possible in graphs with a cycle")
    levels.addLevel(zeroPowers)

    for (vertex in zeroPowers) {
        modifyPowerVector(powers, matrix[vertex])
    }
    for (vertex in zeroPowers) {
        powers[vertex] = null
    }
}

fun modifyPowerVector(powers: Array<Int?>, vector: Array<Int>) {
    for (idx in powers.indices) {
        if (powers[idx] == null) continue
        val power = powers[idx]!! - vector[idx]
        powers[idx] = if (power < 0) null else power
    }
}

fun vectorIsNotEmty(vectorOfPowersOfSemiSource: Array<Int?>): Boolean {
    var result = false
    vectorOfPowersOfSemiSource.forEach { result = result or (it != null) }
    return result
}

fun getVectorOfPowersOfSemiSource(adjacencyMatrix: Array<Array<Int>>): Array<Int?> {
    val vectorOfPowersOfSemiSource: Array<Int?> = Array(adjacencyMatrix.size) {0}
    for (idx in adjacencyMatrix.indices) {
        for (vector in adjacencyMatrix) {
            vectorOfPowersOfSemiSource[idx] = vectorOfPowersOfSemiSource[idx]!! + vector[idx]
        }
    }
    return vectorOfPowersOfSemiSource
}

fun getAdjacencyMatrix(adjacencyVectors: Array<Array<Int>>): Array<Array<Int>> {
    val adjacencyMatrix = Array(adjacencyVectors.size) {Array(adjacencyVectors.size) {0} }
    for (idx in adjacencyVectors.indices) {
        for (vertex in adjacencyVectors[idx]) {
            adjacencyMatrix[idx] [vertex]++
        }
    }
    return adjacencyMatrix
}

private data class Levels(var levels: Array<Array<Int>>) {
    fun addLevel(newLevel: Array<Int>) {
        val copy: Array<Array<Int>> = Array(levels.size + 1) { emptyArray() }
        System.arraycopy(levels, 0, copy, 0, levels.size)
        copy[copy.size - 1] = newLevel
        levels = copy
    }

    fun toReturnArray(): Array<Array<Int>> {
        val returnArray: Array<Array<Int>> = Array(levels.size + 1) { emptyArray() }
        returnArray[0] = (0 until levels.size).toList().toTypedArray()
        for (idx in levels.indices) {
            returnArray[idx + 1] = levels[idx]
        }
        return returnArray
    }
}