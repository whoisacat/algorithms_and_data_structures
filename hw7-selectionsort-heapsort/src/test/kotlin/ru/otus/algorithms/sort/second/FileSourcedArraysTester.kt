package ru.otus.algorithms.sort.second

import ru.otus.algorithms.second.ArraySorter
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
    val prefix = "hw7-selectionsort-heapsort/"
    var measurements: Array<Long>
    measurements = FileSourcedArraysTester().test("0.random", "bubbleSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("bubble random"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("1.digits", "bubbleSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("bubble digits"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("2.sorted", "bubbleSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("bubble sorted"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("3.revers", "bubbleSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("bubble revers"), numberOfDigitsInString)

    measurements = FileSourcedArraysTester().test("0.random", "bubbleSortOpt",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("bubbleOpt random"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("1.digits", "bubbleSortOpt",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("bubbleOpt digits"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("2.sorted", "bubbleSortOpt",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("bubbleOpt sorted"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("3.revers", "bubbleSortOpt",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("bubbleOpt revers"), numberOfDigitsInString)

    measurements = FileSourcedArraysTester().test("0.random", "insertionSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("insertion random"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("1.digits", "insertionSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("insertion digits"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("2.sorted", "insertionSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("insertion sorted"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("3.revers", "insertionSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("insertion revers"), numberOfDigitsInString)

    measurements = FileSourcedArraysTester().test("0.random", "insertionSortShift",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("insertionShift random"), numberOfDigitsInString)
   measurements = FileSourcedArraysTester().test("1.digits", "insertionSortShift",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("insertionShift digits"), numberOfDigitsInString)
   measurements = FileSourcedArraysTester().test("2.sorted", "insertionSortShift",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("insertionShift sorted"), numberOfDigitsInString)
   measurements = FileSourcedArraysTester().test("3.revers", "insertionSortShift",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("insertionShift revers"), numberOfDigitsInString)

    measurements = FileSourcedArraysTester().test("0.random", "insertionSortShiftBinary",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("insShiftBinary random"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("1.digits", "insertionSortShiftBinary",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("insShiftBinary digits"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("2.sorted", "insertionSortShiftBinary",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("insShiftBinary sorted"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("3.revers", "insertionSortShiftBinary",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("insShiftBinary revers"), numberOfDigitsInString)

    measurements = FileSourcedArraysTester().test("0.random", "shellSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("shell random"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("1.digits", "shellSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("shell digits"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("2.sorted", "shellSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("shell sorted"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("3.revers", "shellSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("shell revers"), numberOfDigitsInString)

    measurements = FileSourcedArraysTester().test("0.random", "selectionSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("selection random"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("1.digits", "selectionSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("selection digits"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("2.sorted", "selectionSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("selection sorted"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("3.revers", "selectionSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("selection revers"), numberOfDigitsInString)

    measurements = FileSourcedArraysTester().test("0.random", "heapSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("heap random"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("1.digits", "heapSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("heap digits"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("2.sorted", "heapSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("heap sorted"), numberOfDigitsInString)
    measurements = FileSourcedArraysTester().test("3.revers", "heapSort",
        firstSuffix = 2, latestSuffix = latestSuffix, prefix = prefix)
    printMeasurements(measurements, "%${methodNameLength}s".format("heap revers"), numberOfDigitsInString)
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