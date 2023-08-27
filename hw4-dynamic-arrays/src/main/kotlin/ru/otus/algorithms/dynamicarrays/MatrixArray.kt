package com.whoisacat.edu.otus.algoriths.dynamicarrays

class MatrixArray <T>(private val matrixLong: Int = 32) : DynamicArray<T> {

    private var size: Int = 0
    var matrix: Array<Array<Any?>> = Array(0) { Array(matrixLong) { null } }

    override fun add(item: T, index: Int) {
        if (item == null) throw Exception("unsupported")
        if (index > matrix.size * matrixLong) throw Exception("unsupported")
        val place = calcPlace(index)
        makePlace(place)
        matrix[place[0]] [place[1]] = item
        size++
    }

    private fun makePlace(place: Array<Int>) {
        if (size + 1 > matrixSize()) {
            val copy: Array<Array<Any?>> = Array(matrix.size + 1) { Array(matrixLong) { null } }
            System.arraycopy(matrix, 0, copy, 0, matrix.size)
            matrix = copy
        }
        if (matrix[place[0]] [place[1]] != null) {
            var lastToFirst: Any? = null
            val copyOfExtended: Array<Any?> = Array(matrixLong) {null}
            System.arraycopy(matrix[place[0]], 0, copyOfExtended, 0, place[1])
            System.arraycopy(matrix[place[0]], place[1], copyOfExtended, place[1] + 1,
                matrixLong - (place[1] + 1))
            lastToFirst = matrix[place[0]] [matrixLong - 1]
            matrix[place[0]] = copyOfExtended
            for (i in place[0] + 1 until matrix.size) {
                val copy: Array<Any?> = Array(matrixLong) {null}
                System.arraycopy(matrix[i], 0, copy, 1, matrixLong - 1)
                copy[0] = lastToFirst
                lastToFirst = matrix[i] [matrixLong - 1]
                matrix[i] = copy
            }
        }
    }

    private fun calcPlace(index: Int): Array<Int> {
        val returnArray = Array(2) {0}
        returnArray[0] = index / matrixLong
        returnArray[1] = index - (matrixLong * returnArray[0])
        return returnArray
    }

    private fun matrixSize(): Int {
        if (matrix.size == 0) return 0
        return matrix.size * matrixLong
    }

    override fun remove(index: Int): T {
        if (index > matrixSize() - 1) throw Exception("unsupported")
        val place = calcPlace(index)
        val item = matrix[place[0]] [place[1]]
        matrix[place[0]] [place[1]] = null
        for (i in index..matrixSize() - 2) {
            val nullPlace = calcPlace(i)
            val nextPlace = calcPlace(i + 1)
            if (matrix[nextPlace[0]] [nextPlace[1]] == null) break
            matrix[nullPlace[0]] [nullPlace[1]] = matrix[nextPlace[0]] [nextPlace[1]]
        }
        var latestIsEmpty = true
        for (i in 0 until matrixLong)
            latestIsEmpty = latestIsEmpty && matrix[matrix.size -1] [i] == null
        if (latestIsEmpty) {
            val copy: Array<Array<Any?>> = Array(matrix.size - 1) { Array(matrixLong) { null } }
            System.arraycopy(matrix, 0, copy, 0, matrix.size - 1)
            matrix = copy
        }
        size--
        @Suppress("UNCHECKED_CAST")
        return item as T
    }

    override fun size(): Int {
        return size
    }
}