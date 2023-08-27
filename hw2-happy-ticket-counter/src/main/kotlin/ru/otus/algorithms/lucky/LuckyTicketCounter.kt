package ru.otus.algorithms.lucky

class LuckyTicketCounter {

    private var count: Long = 0

    fun count(n: Int): Long {
        if (n <= 0) throw Exception("n must be grater then zero")
        var sumArray: Array<Long> = Array(10) {1L}
        for (i in 2..n) {
            sumArray = countSumArray(i * 9 + 1, sumArray)
        }
        return sumOfPowers(sumArray)
    }

    private fun countSumArray(n: Int, previousArray: Array<Long>) : Array<Long> {
        val resultArray : Array<Array<Long>> = Array(11)
        { Array(n) { 0 } }
        for (i in 0..9) {
            previousArray.copyInto(resultArray[i], destinationOffset = i)
        }

        val sumArray = Array(n) {0L}
        for (i in 0..< n) {
            for (j in 0..9) {
                sumArray[i] += resultArray[j] [i]
            }
        }
        return sumArray
    }

    private fun sumOfPowers(sumArray: Array<Long>): Long {
        var result = 0L
        for (i in sumArray) {
            result += i * i
        }
        return result
    }

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