package ru.otus.algorithms.sort.fourth

import ru.otus.algorithms.fourth.ArraySorter
import java.io.File
import java.util.stream.Collectors
import java.util.stream.Collectors.toList
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

fun main() {
    val latestSuffix = 7
    val numberOfDigitsInString = 7
    val methodNameLength = 21
    print("%${methodNameLength}s".format("метод и массив"))
    var i = 100
    while (i <= 10_000_000) {
        print("|${"%${numberOfDigitsInString}d".format(i)}")
        i *= 10
    }
    println()
    val prefix = "hw9-countingsort-radixsort-bucketsort/"
    var measurements: Array<Long>
    measurements = FileSourcedArraysTester().test("0.random", "countingSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("counting random"), numberOfDigitsInString)

    measurements = FileSourcedArraysTester().test("1.digits", "countingSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("counting digits"), numberOfDigitsInString)

    measurements = FileSourcedArraysTester().test("0.random", "radixSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("radix random"), numberOfDigitsInString)

    measurements = FileSourcedArraysTester().test("1.digits", "radixSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("radix digits"), numberOfDigitsInString)

    measurements = FileSourcedArraysTester().test("0.random", "bucketSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("bucket random"), numberOfDigitsInString)

    measurements = FileSourcedArraysTester().test("1.digits", "bucketSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("bucket digits"), numberOfDigitsInString)
}

private fun printMeasurements(measurements: Array<Long>, methodName: String, numberOfDigitsInString: Int) {
    println("$methodName|${measurements.asList().stream().map { "%${numberOfDigitsInString}d".format(it) }.collect(Collectors.joining("|"))}")
}

class FileSourcedArraysTester {

    private val directoryName: String =
        "src/test/resources/sorting-tests/"

    fun test(
        dataFolderName: String, methodName: String,
        latestSuffix: Int = 7, firstSuffix: Int = 0, prefix: String = ""
    ): Array<Long> {

        val measurements = Array(latestSuffix + 1 - firstSuffix) {0L}
        for (suffix in firstSuffix..latestSuffix) {
            if (methodName.contains("radix") && !dataFolderName.contains("digits") && suffix > 4) {
                measurements[suffix - firstSuffix] = 0
                continue
            }
            if (methodName.contains("bubble") && suffix > 4) {
                measurements[suffix - firstSuffix] = 0
                continue
            }
            if ((methodName.contains("ins") || methodName.contains("select")) && suffix > 5) {
                measurements[suffix - firstSuffix] = 0
                continue
            }
            if (methodName.contains("bubble") && suffix > 5) {
                measurements[suffix - firstSuffix] = 0
                continue
            }
            if ((methodName.contains("ins") || methodName.contains("select")) && suffix > 6) {
                measurements[suffix - firstSuffix] = 0
                continue
            }
            val inFile = File("$prefix$directoryName/$dataFolderName/test.$suffix.in")
            val outFile = File("$prefix$directoryName/$dataFolderName/test.$suffix.out")
            val `in`: MutableList<String> = ArrayList()
            inFile.forEachLine { `in`.add(it) }
            val arraySize = `in`[0].toInt()

            val array: Array<Int> = `in`[1]
                .split(" ")
                .stream()
                .map { it.toInt() }
                .collect(toList())
                .toTypedArray()
            val sorter = ArraySorter(array)
            assertEquals(arraySize, array.size)
            val startTime = System.currentTimeMillis()
            invokeSorting(methodName, sorter)
            measurements[suffix - firstSuffix] = System.currentTimeMillis() - startTime
            val out: MutableList<String> = ArrayList()
            outFile.forEachLine { out.add(it) }

            val expectedArray = out[0]
                .split(" ")
                .stream()
                .map { it.toInt() }
                .collect(toList())
                .toTypedArray()

            assertContentEquals(expectedArray, array, "suffix = $suffix, array.size = ${array.size}")
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