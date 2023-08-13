import org.junit.jupiter.api.Test

import kotlin.math.sin

class MainTest {

    @Test
    fun print1() {
        println("test1")
        val main = Main()
        main.print {x, y -> x > y}
    }

    @Test
    fun print2() {
        println("test2")
        val main = Main()
        main.print {x, y -> x == y}
    }

    @Test
    fun print3() {
        println("test3")
        val main = Main()
        main.print {x, y -> x == 25 - y}
    }

    @Test
    fun print4() {
        println("test4")
        val main = Main()
        main.print {x, y -> x < 25 - y + 6}
    }

    @Test
    fun print5() {
        println("test5")
        val main = Main()
        main.print {x, y -> x == 2 * y || x == 2 * y + 1}
    }

    @Test
    fun print6() {

        println("test6")
        val main = Main()
        main.print {x, y -> x < 10 || y < 10}
    }

    @Test
    fun print7() {
        println("test7")
        val main = Main()
        main.print {x, y -> x > 16 && y > 16}
    }

    @Test
    fun print8() {
        println("test8")
        val main = Main()
        main.print {x, y -> x == 0 || y == 0}
    }

    @Test
    fun print9() {
        println("test9")
        val main = Main()
        main.print {x, y -> x > 11 + y || y > 11 + x}
    }

    @Test
    fun print10() {
        println("test10")
        val main = Main()
        main.print {x, y -> x > y && x < 2 * y + 2}
    }

    @Test
    fun print11() {
        println("test11")
        val main = Main()
        main.print { x, y -> x == 1 || x == 24 || y == 1 || y == 24 }
    }

    @Test
    fun print13() {
        println("test13")
        val main = Main()
        main.print {x, y -> x > 20 - y && x < 30 - y}
    }

    @Test
    fun print14() {
        println("test14")
        val main = Main()
        main.print {x, y -> x < 130 / (y + 1)}
    }

    @Test
    fun print15() {
        println("test15")
        val main = Main()
        main.print {x, y -> x + 9 < y && x + 22 > y || y + 9 < x && y + 22 > x}
    }

    @Test
    fun print16() {
        println("test16")
        val main = Main()
        main.print {x, y -> x > 14 - y && x < 36 - y && x < y + 10 && x > y -10}
    }

    @Test
    fun print17() {
        println("test17")
        val main = Main()
        main.print {x, y -> y > Math.abs(sin(x.toDouble() / 5.5 + 0.5) * 16).toInt() + 9 }
    }

    @Test
    fun print18() {
        println("test18")
        val main = Main()
        main.print {x, y -> (x < 2 || y < 2) && (x > 0 || y > 0)}
    }
}