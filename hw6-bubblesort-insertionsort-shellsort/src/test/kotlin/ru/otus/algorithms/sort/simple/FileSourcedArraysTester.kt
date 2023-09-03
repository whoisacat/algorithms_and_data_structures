package ru.otus.algorithms.sort.simple

import java.io.File
import java.util.stream.Collectors
import java.util.stream.Collectors.toList
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

fun main() {
    val latestSuffix = 6
    val numberOfDigitsInString = 7
    val methodNameLength = 14
    print("%${methodNameLength}s".format("метод"))
    var i = 100
    while (i <= 10_000_000) {
        print("|${"%${numberOfDigitsInString}d".format(i)}")
        i *= 10
    }
    println()
    val prefix = "hw6-bubblesort-insertionsort-shellsort/"
    var measurements: Array<Long>
    measurements = FileSourcedArraysTester().test("0.random", "bubbleSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("bubble"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("0.random", "bubbleSortOpt",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("bubbleOpt"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("0.random", "insertionSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("insertion"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("0.random", "insertionSortShift",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("insertionShift"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("0.random", "insertionSortShiftBinary",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("insShiftBinary"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("0.random", "shellSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("shell"), numberOfDigitsInString)
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
            if (methodName.contains("bubble") && suffix > 5) {
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
            val method = ArraySorter::class.java.getMethod(methodName)
            val startTime = System.currentTimeMillis()
            method.invoke(sorter)
            measurements[suffix - firstSuffix] = System.currentTimeMillis() - startTime
            val out: MutableList<String> = ArrayList()
            outFile.forEachLine { out.add(it) }

            val expectedArray = out[0]
                .split(" ")
                .stream()
                .map { it.toInt() }
                .collect(toList())
                .toTypedArray()

            assertContentEquals(expectedArray, array)
        }
        return measurements
    }
}