import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext
import java.math.RoundingMode

fun main(args: Array<String>) {
    println(power(BigDecimal(1.001), 1000))
    println(fibonachy(100))
    println(isSimple(655360001) == 0)
}

fun fibonachy(i: Int): BigInteger {
    if (i == 1) return BigInteger.ZERO else if (i == 2) return BigInteger.ONE
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

fun isSimple(i: Int): Int {
    val res = 0
    if (i % 2 == 0) return 2
    for (k in i - 2 downTo  2 step 2) {
        if (i % k == 0) return k
    }
    return res
}

fun power(number: BigDecimal, power: Int): BigDecimal {
    if (power  <= 1) return number
    val mc = MathContext(100, RoundingMode.HALF_UP)
    var result = number
    for (i in 2..power) {
        result = result.multiply(number, mc)
    }
    return result
}