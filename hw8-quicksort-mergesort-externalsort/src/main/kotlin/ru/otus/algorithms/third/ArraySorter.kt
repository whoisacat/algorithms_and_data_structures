package ru.otus.algorithms.third

import java.io.File

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

    fun quickSort(left: Int = 0, right: Int = array.size - 1) {
        if (left >= right) return
        val m = split(left, right)
        quickSort(left, m - 1)
        quickSort(m + 1, right)
    }

    private fun split(left: Int, right: Int): Int {
        val gauge = array[right]
        var m = left - 1
        for (j in left .. (right))
            if (array[j] <= gauge)
                swap(++m, j)
        return m
    }

    fun mergeSort(left: Int = 0, right: Int = array.size - 1) {
        if (left >= right) return
        val m = (left + right) / 2
        mergeSort(left, m)
        mergeSort(m + 1, right)
        merge(left, m, right)
    }

    private fun merge(left: Int, middle: Int, right: Int) {
        val copy = Array(right - left + 1) {0}
        var a = left;
        var b = middle + 1
        var c = 0
        while (a <= middle && b <= right) {
            if (array[a] > array[b]) copy[c++] = array[b++]
            else copy[c++] = array[a++]
        }
        while (a <= middle) copy[c++] = array[a++]
        while (b <= right) copy[c++] = array[b++]
        System.arraycopy(copy, 0, array, left, copy.size)
    }

    fun externalSortWithTFilesAndMerge(t: Int) {
        var fileSuffix = 0
        val tools = Array(t) {
            ArrayFilesUtilities(File("./out${++fileSuffix}.txt").absolutePath)
        }
        val counters = Array(t) {0}
        var copyStart = 0
        for (j in 1 .. t) {
            val copy = Array(calcSize(t, j)) {0}
            System.arraycopy(array, copyStart, copy, 0, copy.size)
            copyStart +=copy.size
            val sorter = ArraySorter(copy)
            sorter.quickSort()
            tools[j - 1].write(sorter.array)
            counters[j - 1] += copy.size
        }
        val buffer = Array<Int?>(t) {null}
        fillBuffer(buffer, counters, tools)
        for (i in array.indices) {
            array[i] = buffer[lessIn(buffer)]!!
            buffer[lessIn(buffer)] = null
            fillBuffer(buffer, counters, tools)
        }
    }

    private fun lessIn(buffer: Array<Int?>): Int {
        var max = 0
        for (i in 1 until buffer.size) {
            if (buffer[max] == null) {
                max = i
                continue
            }
            if (buffer[i] != null && buffer[max]!! > buffer[i]!!)
                max = i
        }
        return max
    }

    private fun calcSize(countOfFiles: Int, arrayNumber: Int): Int {
        return if (array.size % countOfFiles != 0) {
            if (arrayNumber < countOfFiles) array.size / countOfFiles + 1
            else array.size - (arrayNumber - 1) * (array.size / countOfFiles + 1)
        } else array.size / countOfFiles
    }

    private fun fillBuffer(
        buffer: Array<Int?>,
        counters: Array<Int>,
        tools: Array<ArrayFilesUtilities>
    ) {
        for (i in buffer.indices) {
            if (buffer[i] == null && counters[i] > 0) {
                buffer[i] = tools[i].getNextNumber(counters[i]--)
            }
        }
    }
}

infix fun IntRange.step(next: (Int) -> Int) =
    generateSequence(first, next).takeWhile { if (first < last) it <= last else it >= last }

infix fun IntProgression.step(next: (Int) -> Int) = (first..last).step(next)