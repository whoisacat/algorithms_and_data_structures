package ru.otus.algorithms.second

fun main(args: Array<String>) {

    val array = arrayOf(8, 3, 4, 2, 6, 1, 7, 3)
    val sorter = ArraySorter(array)
    sorter.selectionSort()
}
