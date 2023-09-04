package ru.otus.algorithms.second

class ArraySorter(private val array: Array<Int>) {

    fun bubbleSort() {
        for (j in array.size - 1 downTo 0)
            for (i in 0 until j)
                if (array[i] > array[i + 1])
                    swap(i, i + 1)
    }

    fun bubbleSortOpt() {
        for (j in array.size - 1 downTo 0) {
            var swapped = false
            for (i in 0 until j)
                if (array[i] > array[i + 1]) {
                    swap(i, i + 1);
                    swapped = true
                }
            if (!swapped) return
        }
    }

    private fun swap(a: Int, b: Int) {
        val tmp = array[a]
        array[a] = array[b]
        array[b] = tmp
    }

    fun insertionSort() {
        for (j in 1 until array.size) {
            val k = array[j]
            var i = j - 1
            while (i >= 0 && array[i] > k) {
                swap(i, i + 1)
                i--
            }
        }
    }

    fun insertionSortShift() {
        for (j in 1 until array.size) {
            val k = array[j]
            var i = j - 1
            while (i >= 0 && array[i] > k) {
                array[i + 1] = array[i]
                i--
            }
            array[i + 1] = k

        }
    }

    fun insertionSortShiftBinary() {
        for (j in 1 until array.size) {
            val k = array[j]
            val p = binarySearch(k, 0, j - 1)
            for (i in j - 1 downTo p) {
                array[i + 1] = array[i]
            }
            array[p] = k
        }
    }

    private fun binarySearch(k: Int, low: Int, high: Int): Int {
        return if (high <= low) {
            if (k >= array[low]) low + 1 else low
        } else {
            val mid = (low + high) / 2
            if (k >= array[mid]) binarySearch(k, mid + 1, high)
            else binarySearch(k, low, mid - 1)
        }
    }

    fun shellSort() {
        if (array.size < 2) return
        for (gap in array.size / 2 downTo  1 step {it / 2})
            for (j in gap until array.size) {
                var i = j
                while (i >= gap && array[i - gap] > array[i]) {
                    swap(i - gap, i)
                    i -= gap
                }
            }
    }

    fun selectionSort() {
        for (j in array.size - 1 downTo 1)
            swap(findMax(j), j)
    }

    private fun findMax(j: Int): Int {
        var max = 0
        for (i in 0 .. j)
            if (array[i] > array[max]) max = i
        return max
    }

    fun heapSort() {
        for (root in array.size / 2 - 1 downTo 0) {
            heapify(root, array.size)
        }
        for (j in array.size - 1 downTo 1) {
            swap(0, j)
            heapify(0, j)
        }

    }

    private fun heapify(root: Int, size: Int) {
        val max = findMaxInHeap(root, size)
        if (root == max) return
        swap(max, root)
        heapify(max, size)
    }

    private fun findMaxInHeap(root: Int, size: Int): Int {
        var max = root
        var child = leftChild(root)
        if (child < size && array[max] < array[child]) max = child
        child++
        if (child < size && array[max] < array[child]) max = child
        return max
    }

    private fun leftChild(i: Int): Int {
        return 2 * i + 1
    }
}

infix fun IntRange.step(next: (Int) -> Int) =
    generateSequence(first, next).takeWhile { if (first < last) it <= last else it >= last }

infix fun IntProgression.step(next: (Int) -> Int) = (first..last).step(next)