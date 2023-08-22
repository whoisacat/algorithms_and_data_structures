package ru.otus.algorithms.algebraic

import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext
import java.math.RoundingMode

fun main(args: Array<String>) {
    runPowerDemo()
    runFibonacciDemo()
    runCountSimples()
}

//01. +1 байт. Реализовать итеративный O(N) алгоритм возведения числа в степень.
fun powerIteration(number: BigDecimal, power: Int): BigDecimal {
    if (power == 0) return BigDecimal.ONE
    if (power  <= 1) return number
    val mc = MathContext(100, RoundingMode.HALF_UP)
    var result = number
    for (i in 2..power) {
        result = result.multiply(number, mc)
    }
    return result
}

//02. +1 байт. Реализовать рекурсивный O(2^N) и итеративный O(N) алгоритмы поиска чисел Фибоначчи.

fun fibonacciRecursively(i: Int): BigInteger {
    if (i <= 1) return BigInteger.valueOf(i.toLong())
    return fibonacciRecursively(i - 1) + fibonacciRecursively(i - 2)
}

fun fibonacciIteratively(i: Int): BigInteger {
    if (i == 0) return BigInteger.ZERO else if (i == 1) return BigInteger.ONE else if (i == 2) return BigInteger.ONE
    var f = BigInteger.ZERO
    var s = BigInteger.ONE
    var res = BigInteger.ZERO
    for (k in 3..i + 1) {
        res = f + s
        f = s
        s = res
    }
    return res
}
//03. +1 байт. Реализовать алгоритм поиска количества простых чисел через перебор делителей, O(N^2).

fun countSimplesN2(n: Int): Int {
    var count = 0;
    for (i in 1..n) {
        if (isSimple(i)) count++
    }
    return count
}

fun isSimple(i: Int): Boolean {
    if (i % 2 == 0) return false
    for (k in i - 2 downTo  2 step 2) {
        if (i % k == 0) return false
    }
    return true
}

//11. +1 байт. Реализовать алгоритм возведения в степень через домножение O(N/2+LogN) = O(N).
fun powerFactor(number: BigDecimal, power: Int): BigDecimal {
    val mc = MathContext(100, RoundingMode.HALF_UP)
    val squareTimes = findOutSquareTimes(power)
    val secondPower = power - (1 shl squareTimes)
    var first = number
    for (i in 1 .. squareTimes) {
        first = first.multiply(first, mc)
    }
    val second = powerIteration(number, secondPower)
    return first.multiply(second, mc)
}

fun powerFactor2(number: BigDecimal, power: Int): BigDecimal {
    if (power == 0) return BigDecimal.ONE
    if (power == 1) return number
    val mc = MathContext(100, RoundingMode.HALF_UP)
    val squareTimes = findOutSquareTimes(power)
    val secondPower = power - (1 shl squareTimes)
    var first = number
    for (i in 1 .. squareTimes) {
        first = first.multiply(first, mc)
    }
    return if (secondPower % 2 == 0) {
        first.multiply(powerFactor2(number, secondPower), mc)
    } else {
        val second = powerIteration(number, secondPower)
        first.multiply(second, mc)
    }
}

private fun findOutSquareTimes(power: Int): Int {
    var squareTimes = 0
    var shifted = power
    while (shifted > 1) {
        shifted = shifted shr 1
        squareTimes++
    }
    return squareTimes
}
//12. +1 байт. Реализовать алгоритм возведения в степень через двоичное разложение показателя
// степени O(2LogN) = O(LogN).
fun powerWithBinaryPowerExpansion(number: BigDecimal, power: Int): BigDecimal {
    if (power == 0) return BigDecimal.ONE
    if (power == 1) return number
    val mc = MathContext(100, RoundingMode.HALF_UP)
    if (power % 2 == 1) return powerWithBinaryPowerExpansion(number, power - 1).multiply(number, mc)
    val interim = powerWithBinaryPowerExpansion(number, power / 2)
    return interim.multiply(interim, mc)
}
//13. +1 байт. Реализовать алгоритм поиска чисел Фибоначчи по формуле золотого сечения.
fun fibonacciGoldenRatio(i: Int): BigInteger {

    val mc = MathContext(100, RoundingMode.HALF_UP)
    val rootOf5 = BigDecimal(5).sqrt(mc)
    return BigDecimal.ONE
        .plus(rootOf5)
        .divide(BigDecimal(2), mc)
        .pow(i)
        .divide(rootOf5, mc)
        .plus(BigDecimal(0.5))
        .toBigInteger()
}
//14. +1 байт. Написать класс умножения матриц, реализовать алгоритм возведения матрицы в степень через
// двоичное разложение показателя степени, реализовать алгоритм поиска чисел Фибоначчи O(LogN) через
// умножение матриц, используя созданный класс.
fun fibonacciWithMatrix(f: Int): BigInteger {
    if (f == 0) return BigInteger.ZERO
    if (f == 1) return BigInteger.ONE
    val matrixArray = Array(2) { Array(2) { BigInteger.ONE } }
    matrixArray[1] [1] = BigInteger.ZERO
    val qMatrix = TwoDimensionMatrix(matrixArray)
    return qMatrix.power(f - 1).matrix[0] [0]
}
//15. +1 байт. Реализовать алгоритм поиска простых чисел с оптимизациями поиска и делением только на простые
// числа, O(N * Sqrt(N) / Ln (N)).
fun searchSimplesOptimised(n: Int) : Int {
    var count = 0
    for (i in 0..n) {
        if (isSimpleOptimised(i)) count++
    }
    return count
}

fun isSimpleOptimised(i: Int): Boolean {
    if (i < 4) return true
    if (i % 2 == 0) return false
    val lim = Math.sqrt(i.toDouble()).toInt()
    for (k in 5..lim step 2) {
        if (i % k == 0) return false
    }
    return true
}
//16. +1 байт. Реализовать алгоритм "Решето Эратосфена" для быстрого поиска простых чисел O(N Log Log N).
fun eratostheneSimple(n : Int): Int {
    val sieve = Array(n) {true}
    var count = 0
    for (p in 2..n) {
        if (!sieve[p - 1]) continue
        count++
        for (i in 2*p .. n step p) {
            sieve[i - 1] = false
        }
    }
    return count
}
//17. +1 байт. Решето Эратосфена с оптимизацией памяти, используя битовую матрицу, сохраняя по 32 значения
// в одном int, хранить биты только для нечётных чисел.

fun eratostheneOptimisedByMemory(n: Long): Long {
    val sieve = Array(numberOfInt(n.toInt())) {0L}
    var count = 0L
    if (n > 1) count++
    for (p in 2..n) {
        if (p % 2 == 0L) continue
        val placeOfFlagAndShift = calculatePlaceOfFlagAndShift(p)
        val intNumber = placeOfFlagAndShift[0].toInt()
        val shift = placeOfFlagAndShift[1].toInt()
        if (sieve[intNumber] and (1L shl shift) > 0) continue
        count++
        for (i in p * p .. n step p) {
            if (i % 2 == 0L) continue
            placeFlag(sieve, i)
        }
    }
    return count
}

private fun numberOfInt(n: Int): Int {
    return if (n % 64 == 0) n / 64 else n / 64 + 1
}

private fun placeFlag(sieve: Array<Long>, i: Long) {
    val placeOfFlagAndShift = calculatePlaceOfFlagAndShift(i)
    val intNumber = placeOfFlagAndShift[0]
    val shift = placeOfFlagAndShift[1]
    // с арифметическим сложением не работает даже в лонге из-за знака. не знаю почему
    sieve[intNumber.toInt()] = sieve[intNumber.toInt()] or (1L shl shift.toInt())
}

private fun calculatePlaceOfFlagAndShift(n: Long): Array<Long> {
    val order = n / 2L
    val result = Array(2) {0L}
    result[0] = arrayCell(n)
    result[1] = if (order <= 31) order else order - (result[0] * 32)
    return result
}

private fun arrayCell(n: Long): Long {
    return n / 64
}

//18. +1 байт. Решето Эратосфена со сложностью O(n), см. дополнительный материал.
//todo

fun runCountSimples() {
    println("простые числа")
    println("номер | перебор делителей | Решето Эратосфена | Решето Эратосфена с оптимизацией памяти")
    for (i in 1..6) {
        val mes: Array<Long> = Array(3) { 0 }
        println( executeSimple(Math.pow(10.0, i.toDouble()).toInt(), mes))
        println("${Math.pow(10.0, i.toDouble()).toInt()}\t|${mes[0]}\t| ${mes[1]}\t| ${mes[2]}")
    }
}

private fun executeSimple(number: Int, res: Array<Long>): String {
    var start = System.nanoTime()
    val countSimplesN2 = countSimplesN2(number)
    res[0] = System.nanoTime() - start
    start = System.nanoTime()
    val eratostheneSimple = eratostheneSimple(number)
    res[1] = System.nanoTime() - start
    start = System.nanoTime()
    val eratostheneOptimisedByMemory = eratostheneOptimisedByMemory(number.toLong()).toInt()
    res[2] = System.nanoTime() - start
    return "простых чисел [$countSimplesN2] разультаты слвпадают: " +
            "${countSimplesN2 == eratostheneSimple
                    && eratostheneSimple == eratostheneOptimisedByMemory}"
}

private fun runFibonacciDemo() {
    println("fibonacci")
    println("номер | рекурсивно | итеративно | золотое | матрично |")

    val res = Array<Long>(4) { 0 }

    for (i in 9..40) {
        val explanation = executeFibonacci(i, res)
        println("${i}\t|${res[0]} | ${res[1]} | ${res[2]} | ${res[3]} | $explanation")
    }
}

private fun executeFibonacci(number: Int, res: Array<Long>): String {
    var start = System.nanoTime()
    val fibonacciRecursively = fibonacciRecursively(number)
    res[0] = System.nanoTime() - start
    start = System.nanoTime()
    val fibonacciIteratively = fibonacciIteratively(number)
    res[1] = System.nanoTime() - start
    start = System.nanoTime()
    val fibonacciGoldenRatio = fibonacciGoldenRatio(number)
    res[2] = System.nanoTime() - start
    start = System.nanoTime()
    val fibonacciWithMatrix = fibonacciWithMatrix(number)
    res[3] = System.nanoTime() - start
    return "число фибоначи [$fibonacciGoldenRatio] разультаты слвпадают: " +
            "${fibonacciRecursively == fibonacciIteratively 
                    && fibonacciGoldenRatio == fibonacciWithMatrix
                    && fibonacciRecursively == fibonacciGoldenRatio}"
}

private fun runPowerDemo() {
    val res = Array<Long>(4) { 0 }
    println("cтепень | итеративно | домножение | воу домножение | раздлжение")
    executePowers(1, res)
    println("1 \t|${res[0]} | ${res[1]} | ${res[2]} | ${res[3]}")
    executePowers(2, res)
    println("2 \t|${res[0]} | ${res[1]} | ${res[2]} | ${res[3]}")

    for (i in 1..7) {
        executePowers(Math.pow(10.0, i.toDouble()).toInt(), res)
        println("${Math.pow(10.0, i.toDouble()).toInt()}\t|${res[0]} | ${res[1]} | ${res[2]} | ${res[3]}")
    }
}

private fun executePowers(power: Int, res: Array<Long>) {
    var start = System.nanoTime()
    val number = 2
    print("${powerIteration(BigDecimal(number), power)} ")
    res[0] = System.nanoTime() - start
    start = System.nanoTime()
    print("${powerFactor(BigDecimal(number), power)} ")
    res[1] = System.nanoTime() - start
    start = System.nanoTime()
    print("${powerFactor2(BigDecimal(number), power)} ")
    res[2] = System.nanoTime() - start
    start = System.nanoTime()
    println("${powerWithBinaryPowerExpansion(BigDecimal(number), power)}")
    res[3] = System.nanoTime() - start
}
