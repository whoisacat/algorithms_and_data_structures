import java.util.function.BiPredicate

class Main {
    fun print(predicate: BiPredicate<Int, Int>) {
        val height = 50
        val width = 50

        for (x in (0..25)) {
            for (y in 0..25) {
                if (predicate.test(y, x)) print("# ") else print(". ")
            }
            println()
        }
    }
}