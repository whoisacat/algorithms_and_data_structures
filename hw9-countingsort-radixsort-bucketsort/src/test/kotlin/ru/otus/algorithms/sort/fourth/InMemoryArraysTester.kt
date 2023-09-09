package ru.otus.algorithms.sort.fourth

import ru.otus.algorithms.fourth.ArraySorter
import java.util.Random
import java.util.stream.Collectors
import kotlin.test.assertEquals
import kotlin.test.assertTrue

fun main() {

    val numberOfDigitsInString = 7
    val methodNameLength = 21
    print("%${methodNameLength}s".format("метод и массив"))
    var i = 100
    while (i <= 1_000_000) {
        print("|${"%${numberOfDigitsInString}d".format(i)}")
        i *= 10
    }
    println()
    var measurements: Array<Long>
    measurements = InMemoryArraysTester().test("countingSort")
    printMeasurements(measurements, "%${methodNameLength}s".format("counting 0..999"), numberOfDigitsInString)

    measurements = InMemoryArraysTester().test("radixSort")
    printMeasurements(measurements, "%${methodNameLength}s".format("radix 0..999"), numberOfDigitsInString)

    measurements = InMemoryArraysTester().test("bucketSort")
    printMeasurements(measurements, "%${methodNameLength}s".format("bucket 0..999"), numberOfDigitsInString)
}

private fun printMeasurements(measurements: Array<Long>, methodName: String, numberOfDigitsInString: Int) {
    println("$methodName|${measurements.asList().stream().map { "%${numberOfDigitsInString}d".format(it) }.collect(Collectors.joining("|"))}")
}

class InMemoryArraysTester {

    fun test(methodName: String): Array<Long> {

        val measurements = Array(5) {0L}
        val numbers = arrayOf(100, 1_000, 10_000, 100_000, 1_000_000)
        for (number in numbers) {

            val random = Random()
            val array: Array<Int> = Array(number) {random.nextInt(1000)}
            val sorter = ArraySorter(array)
            assertEquals(number, array.size)
            val startTime = System.currentTimeMillis()
            invokeSorting(methodName, sorter)
            measurements[numbers.indexOf(number)] = System.currentTimeMillis() - startTime

            for (idx in 1 until array.size) {
                assertTrue(array[idx - 1] <= array[idx],
                    "array[${idx - 1}] = ${array[idx - 1]}, array[${idx}] = ${array[idx]}")
            }
        }
        return measurements
    }

    private fun invokeSorting(methodName: String, sorter: ArraySorter) {
        if (methodName.equals("countingSort")) sorter.countingSort()
        if (methodName.equals("radixSort")) sorter.radixSort()
        if (methodName.equals("bucketSort")) sorter.bucketSort()
        if (methodName.equals("shellSort")) sorter.shellSort()
        if (methodName.equals("selectSort")) sorter.selectionSort()
        if (methodName.equals("heapSort")) sorter.heapSort()
        if (methodName.equals("quickSort")) sorter.quickSort()
        if (methodName.equals("mergeSort")) sorter.mergeSort()
        if (methodName.contains("externalSortWithTFilesAndMerge")) sorter.externalSortWithTFilesAndMerge(8)
    }
}