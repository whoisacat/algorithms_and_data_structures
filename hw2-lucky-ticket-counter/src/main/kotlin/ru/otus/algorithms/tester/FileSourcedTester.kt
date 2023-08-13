package ru.otus.algorithms.tester

import ru.otus.algorithms.lucky.LuckyTicketCounter
import java.io.File
import java.time.LocalDateTime

fun main() {
    FileSourcedTester().test()
}
class FileSourcedTester {

    private val directoryName: String = "./hw2-lucky-ticket-counter/testFiles"
    fun test() {
        val current  = File(".")
        println("exists ${current.exists()} ${current.absolutePath}")
        for (suffix in 0..9) {
            val inFile = File("$directoryName/test.$suffix.in")
            val outFile = File("$directoryName/test.$suffix.out")
            val `in`: MutableList<Int> = ArrayList()
            inFile.forEachLine { `in`.add(it.toInt()) }
            val counter = LuckyTicketCounter()
            val result = counter.countExponentially(`in`[0])
            val out: MutableList<Long> = ArrayList()
            outFile.forEachLine { out.add(it.toLong()) }
            println("${LocalDateTime.now()} n == $`in` result == $result reference == ${out[0]} test ${if (out.get(0) == result) "ok" else "failed"}")
        }
    }
}