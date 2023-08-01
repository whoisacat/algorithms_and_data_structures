import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MainTest {

    @Test
    fun print1() {
        val main = Main()
        main.print {x, y -> x > y}
    }

    @Test
    fun print2() {
        val main = Main()
        main.print {x, y -> x == y}
    }

    @Test
    fun print3() {
        val main = Main()
        main.print {x, y -> x == 25 - y}
    }

    @Test
    fun print4() {
        val main = Main()
        main.print {x, y -> x < 25 - y + 6}
    }

    @Test
    fun print5() {
        val main = Main()
        main.print {x, y -> x == 2 * y || x == 2 * y + 1}
    }

    @Test
    fun print6() {
        val main = Main()
        main.print {x, y -> x < 10 || y < 10}
    }

    @Test
    fun print7() {
        val main = Main()
        main.print {x, y -> x > 16 && y > 16}
    }

    @Test
    fun print8() {
        val main = Main()
        main.print {x, y -> x == 0 || y == 0}
    }

    @Test
    fun print9() {
        val main = Main()
        main.print {x, y -> x > 11 + y || y > 11 + x}
    }

    @Test
    fun print10() {
        val main = Main()
        main.print {x, y -> x > y && x < 2 * y + 2}
    }

    @Test
    fun print11() {
        val main = Main()
        main.print { x, y -> x == 1 || x == 24 || y == 1 || y == 24 }
    }

    @Test
    fun print12() {
        val main = Main()
        main.print {x, y -> val shift = 4
            (x + shift) * (x + shift) + (y + shift) * (y + shift) < 700 }
    }
}