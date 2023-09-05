package ru.otus.algorithms.sort.third

import org.junit.jupiter.api.Test

class Test {

    private val latestSuffix = 7

    @Test
    fun testBubbleSortRandom() {
        FileSourcedArraysTester().test(dataFolderName = "0.random", methodName = "bubbleSort", latestSuffix = latestSuffix)
    }

//    @Test
//    fun testBubbleSortOptRandom() {
//        FileSourcedArraysTester().test(dataFolderName = "0.random", methodName = "bubbleSortOpt", latestSuffix = latestSuffix)
//    }

    @Test
    fun testInsertionSortRandom() {
        FileSourcedArraysTester().test(dataFolderName = "0.random", methodName = "insertionSort", latestSuffix = latestSuffix)
    }

    @Test
    fun testShellSortRandom() {
        FileSourcedArraysTester().test(dataFolderName = "0.random", methodName = "shellSort", latestSuffix = latestSuffix)
    }

//    @Test
//    fun testInsertionSortOptRandom() {
//        FileSourcedArraysTester().test(dataFolderName = "0.random", methodName = "insertionSortOpt", latestSuffix = latestSuffix)
//    }

//    @Test
//    fun testShellSortOptRandom() {
//        FileSourcedArraysTester().test(dataFolderName = "0.random", methodName = "shellSortOpt", latestSuffix = latestSuffix)
//    }

    @Test
    fun testBubbleSortDigits() {
        FileSourcedArraysTester().test(dataFolderName = "1.digits", methodName = "bubbleSort", latestSuffix = latestSuffix)
    }

//    @Test
//    fun testBubbleSortOptDigits() {
//        FileSourcedArraysTester().test(dataFolderName = "1.digits", methodName = "bubbleSortOpt", latestSuffix = latestSuffix)
//    }

    @Test
    fun testInsertionSortDigits() {
        FileSourcedArraysTester().test(dataFolderName = "1.digits", methodName = "insertionSort", latestSuffix = latestSuffix)
    }

    @Test
    fun testShellSortDigits() {
        FileSourcedArraysTester().test(dataFolderName = "1.digits", methodName = "shellSort", latestSuffix = latestSuffix)
    }

    @Test
    fun testBubbleSortSorted() {
        FileSourcedArraysTester().test(dataFolderName = "2.sorted", methodName = "bubbleSort", latestSuffix = latestSuffix)
    }

//    @Test
//    fun testBubbleSortOptSorted() {
//        FileSourcedArraysTester().test(dataFolderName = "2.sorted", methodName = "bubbleSortOpt", latestSuffix = latestSuffix)
//    }

    @Test
    fun testInsertionSortSorted() {
        FileSourcedArraysTester().test(dataFolderName = "2.sorted", methodName = "insertionSort", latestSuffix = latestSuffix)
    }

    @Test
    fun testShellSortSorted() {
        FileSourcedArraysTester().test(dataFolderName = "2.sorted", methodName = "shellSort", latestSuffix = latestSuffix)
    }

    @Test
    fun testBubbleSortRevers() {
        FileSourcedArraysTester().test(dataFolderName = "3.revers", methodName = "bubbleSort", latestSuffix = latestSuffix)
    }

//    @Test
//    fun testBubbleSortOptRevers() {
//        FileSourcedArraysTester().test(dataFolderName = "3.revers", methodName = "bubbleSortOpt", latestSuffix = latestSuffix)
//    }

    @Test
    fun testInsertionSortRevers() {
        FileSourcedArraysTester().test(dataFolderName = "3.revers", methodName = "insertionSort", latestSuffix = latestSuffix)
    }

    @Test
    fun testShellSortRevers() {
        FileSourcedArraysTester().test(dataFolderName = "3.revers", methodName = "shellSort", latestSuffix = latestSuffix)
    }
}