package ru.otus.algorithms.third

fun main(args: Array<String>) {

    val array = arrayOf(8, 3, 4, 2, 6, 1, 7, 3, 0)
    val sorter = ArraySorter(array)
    sorter.mergeSort()
}
