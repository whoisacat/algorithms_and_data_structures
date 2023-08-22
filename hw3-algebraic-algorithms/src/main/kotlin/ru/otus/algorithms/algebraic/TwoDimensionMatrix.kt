package ru.otus.algorithms.algebraic

import java.lang.Exception
import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext
import java.math.RoundingMode

class TwoDimensionMatrix(val matrix: Array<Array<BigInteger>>) {

    fun multiply(second: TwoDimensionMatrix): TwoDimensionMatrix {
        if (this.matrix.size != second.matrix[0].size
            || second.matrix.size != this.matrix[0].size)
            throw Exception("wrong matrix's sizes")
        val result: Array<Array<BigInteger>> =
            Array(this.matrix.size) { Array(this.matrix.size) { BigInteger.ZERO } }
        for (j in this.matrix.indices) {
            for (k in second.matrix[j].indices) {
                val fromSecond: Array<BigInteger> = Array(matrix[k].size) { BigInteger.ZERO }
                for (i in this.matrix[k].indices) {
                    fromSecond[i] = second.matrix[i] [k]
                }
                for (i in matrix[j].indices) {
                    result[j] [k] += matrix[j] [i].multiply(fromSecond[i])
                }
            }
        }
        return TwoDimensionMatrix(result)
    }

    fun power(power: Int): TwoDimensionMatrix {
        if (power == 0) throw Exception("not supported")
        if (power == 1) return TwoDimensionMatrix(this.matrix.clone())
        val mc = MathContext(100, RoundingMode.HALF_UP)
        if (power % 2 == 1) {
            return this.multiply(power(power - 1))
        }
        var interim = power(power / 2)
        return interim.multiply(interim)
    }
}