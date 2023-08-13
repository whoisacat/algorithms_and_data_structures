package ru.otus.algorithms.lucky

class LuckyTicketCounter {

    private var count: Long = 0
    fun countExponentially(n: Int): Long {

        val sumA: Long = 0
        val sumB: Long = 0
        countExponentially(n, sumA, sumB)
        return count
    }

    private fun countExponentially(n: Int, sumA: Long, sumB: Long) {
        if (n < 1) {
            if (sumA == sumB) count++
            return
        }
        for (a in 0..9) {
            for (b in 0..9) {
                countExponentially(n - 1, sumA + a, sumB + b)
            }
        }
    }
}