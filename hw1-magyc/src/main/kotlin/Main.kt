import java.util.function.BiPredicate

class Main {
    fun print(predicate: BiPredicate<Int, Int>) {
        val height = 25
        val width = 25

        for (x in (0..width)) {
            for (y in 0..height) {
                if (predicate.test(y, x)) print("# ") else print(". ")
            }
            println()
        }
    }
}