package ru.otus.algorithms.work.course

import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.nio.charset.Charset

fun main() {
    val file = File("./course-work/src/main/resources/book.txt")
    val reader = FileReader(file, Charset.forName("Windows-1251"))
    val trie = Trie()
    reader.readLines().stream()
        .flatMap { it.split("\\p{Punct}+".toRegex()).stream() }
        .map { it.replace(Char(160), ' ') }
        .flatMap { it.split("\\s".toRegex()).stream() }
        .map { it.replace("[»«\\t\\s]".toRegex(), "") }
        .filter {it.contains("\\D".toRegex())}
        .map { it.replace("\\d".toRegex(), "") }
        .map { it.lowercase() }
        .filter {it.length > 3} // подсказки даются от трех букв
        .peek {trie.put(it)}
        .forEach(::println)
    println(trie.size())
    val nextLetterWords = trie.getNextLetterWords()
    println("${nextLetterWords.size} ${nextLetterWords.joinToString(",")}")
    val nextLetterWordsInDepth = trie.getNextLetterWordsInDepth(5)
    println("${nextLetterWordsInDepth.size} ${nextLetterWordsInDepth.joinToString(",")}")
    val nextLetterWordsInDepth6 = trie.getNextLetterWordsInDepth(6)
    println("${nextLetterWordsInDepth6.size} ${nextLetterWordsInDepth6.joinToString(",")}")
    val initialDataFile = File("./course-work/src/main/resources/init-data.txt")
    initialDataFile.createNewFile()
    val writer = FileWriter(initialDataFile)
    writer.write(nextLetterWordsInDepth6.joinToString(","))
    writer.close()
    println("length ${initialDataFile.length()}")

    //49
    //4538 богатейшей,богатой,богатству,богатые,богаче
    //5979 богатейшей,богатой,богатству,богатые,богатый,богатым,богатых,богаче
}