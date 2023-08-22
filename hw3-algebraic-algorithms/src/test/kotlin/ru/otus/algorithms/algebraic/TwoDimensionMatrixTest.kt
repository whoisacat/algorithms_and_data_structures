package ru.otus.algorithms.algebraic

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigInteger

class TwoDimensionMatrixTest {

    @Test
    fun multiply2x2() {
        val matrix = Array(2) { Array(2) { BigInteger.ONE } }
        matrix[1] [1] = BigInteger.ZERO
        val res = TwoDimensionMatrix(matrix).multiply(TwoDimensionMatrix((matrix.clone())))
        for (i in res.matrix.indices) {
            for (j in res.matrix.indices)
                print("${res.matrix[i][j]} ")
            println()
        }
        val expected = Array(2) { Array(2) { BigInteger.ONE } }
        expected[0] [0] = BigInteger.TWO
        for (i in expected.indices) {
            for (j in expected.indices)
                print("${expected[i][j]} ")
            println()
        }

        assertTrue(expected.size == res.matrix.size)
        for (i in res.matrix.indices) {
            for (j in res.matrix.indices)
                assertEquals(expected[i] [j], res.matrix[i] [j])
        }
    }

    @Test
    fun multiply3x2() {
        val matrix1 = Array(2) { Array(3) { BigInteger.ONE } }
        matrix1[0] [1] = BigInteger.TWO
        matrix1[0] [2] = BigInteger.TWO
        matrix1[1] [0] = BigInteger.valueOf(3)

        println("первая")
        for (i in matrix1.indices) {
            for (j in matrix1[i].indices)
                print("${matrix1[i][j]} ")
            println()
        }


        val matrix2 = Array(3) { Array(2) { BigInteger.ONE } }
        matrix2[0] [0] = BigInteger.valueOf(4)
        matrix2[0] [1] = BigInteger.TWO
        matrix2[1] [0] = BigInteger.valueOf(3)
        matrix2[2] [1] = BigInteger.valueOf(5)

        println("вторая")
        for (i in matrix2.indices) {
            for (j in matrix2[i].indices)
                print("${matrix2[i][j]} ")
            println()
        }

        val res = TwoDimensionMatrix(matrix1).multiply(TwoDimensionMatrix((matrix2)))
        for (i in res.matrix.indices) {
            for (j in res.matrix.indices)
                print("${res.matrix[i][j]} ")
            println()
        }
        val expected = Array(2) { Array(2) { BigInteger.valueOf(12) } }
        expected[0] [1] = BigInteger.valueOf(14)
        expected[1] [0] = BigInteger.valueOf(16)
        for (i in expected.indices) {
            for (j in expected[i].indices)
                print("${expected[i][j]} ")
            println()
        }

        assertTrue(expected.size == res.matrix.size)
        for (i in res.matrix.indices) {
            for (j in res.matrix.indices)
                assertEquals(expected[i] [j], res.matrix[i] [j])
        }
    }

    @Test
    fun multiply3x3() {
        val matrix1 = Array(3) { Array(3) { BigInteger.ONE } }
        matrix1[0] [1] = BigInteger.valueOf(4)
        matrix1[0] [2] = BigInteger.valueOf(3)
        matrix1[1] [0] = BigInteger.valueOf(2)
        matrix1[1] [2] = BigInteger.valueOf(5)
        matrix1[2] [0] = BigInteger.valueOf(3)
        matrix1[2] [1] = BigInteger.valueOf(2)
        println("первая")
        for (i in matrix1.indices) {
            for (j in matrix1[i].indices)
                print("${matrix1[i][j]} ")
            println()
        }
        val matrix2 = Array(3) { Array(3) { BigInteger.TWO } }
        matrix2[0] [0] = BigInteger.valueOf(5)
        matrix2[0] [2] = BigInteger.ONE
        matrix2[1] [0] = BigInteger.valueOf(4)
        matrix2[1] [1] = BigInteger.valueOf(3)
        matrix2[2] [1] = BigInteger.valueOf(1)
        matrix2[2] [2] = BigInteger.valueOf(5)
        println("вторая")
        for (i in matrix2.indices) {
            for (j in matrix2[i].indices)
                print("${matrix2[i][j]} ")
            println()
        }
        val res = TwoDimensionMatrix(matrix1).multiply(TwoDimensionMatrix((matrix2)))
        println("резкльтат")
        for (i in res.matrix.indices) {
            for (j in res.matrix.indices)
                print("${res.matrix[i][j]} ")
            println()
        }
        val expected = Array(3) { Array(3) { BigInteger.valueOf(24) } }
        expected[0] [0] = BigInteger.valueOf(27)
        expected[0] [1] = BigInteger.valueOf(17)
        expected[1] [1] = BigInteger.valueOf(12)
        expected[1] [2] = BigInteger.valueOf(29)
        expected[2] [0] = BigInteger.valueOf(25)
        expected[2] [1] = BigInteger.valueOf(13)
        expected[2] [2] = BigInteger.valueOf(12)
        println("ожидаем")
        for (i in expected.indices) {
            for (j in expected[i].indices)
                print("${expected[i][j]} ")
            println()
        }
        assertTrue(expected.size == res.matrix.size)
        for (i in res.matrix.indices) {
            for (j in res.matrix.indices)
                assertEquals(expected[i] [j], res.matrix[i] [j])
        }
    }
}