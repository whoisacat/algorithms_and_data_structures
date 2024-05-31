package ru.otus.algorithms.dynamic.programming

fun printDigitalTree(n: Int) {
    for (i in 1..n) {
        // Печатаем i раз цифру i
        println(i.toString().repeat(i))
    }
}

fun main() {
    val n = 7  // Количество строк в ёлочке
    printDigitalTree(n)
}
