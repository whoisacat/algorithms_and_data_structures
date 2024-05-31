package ru.otus.algorithms.dynamic.programming

fun countWays(n: Int): Int {
    // Массив для хранения количества способов
    val dp = IntArray(n + 1)

    // Инициализация базовых случаев
    dp[0] = 1  // 1 способ добраться до 0-й позиции
    if (n >= 1) {
        dp[1] = 1  // 1 способ добраться до 1-й позиции (один шаг раз)
    }

    // Заполнение массива dp с использованием рекуррентного соотношения
    for (i in 2..n) {
        dp[i] = dp[i - 1] + dp[i - 2]
    }

    return dp[n]
}

fun main() {
    val n = 10  // Пример для n = 10
    println("Количество способов добраться до позиции $n: ${countWays(n)}")
}