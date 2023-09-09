package ru.otus.algorithms.fourth

fun main(args: Array<String>) {

//    var file = ArrayFilesUtilities("./file.txt")
//    file.generateFileWithRandomIntArray(10, 12)

    val array = arrayOf(8, 3, 4, 2, 9, 1, 7, 3, 0, 5)
    val sorter = ArraySorter(array)
    sorter.bucketSort()
//    sorter.externalSortWithTFilesAndMerge(3)
    for (i in array) print("$i ")
}
