package ru.otus.algorithms.work.course.persistence

import org.springframework.stereotype.Component
import ru.otus.algorithms.work.course.domain.Trie
import ru.otus.algorithms.work.course.domain.hash.Cache
import ru.otus.algorithms.work.course.domain.hash.QueuedLRUCache
import ru.otus.algorithms.work.course.referentialBookPath
import ru.otus.algorithms.work.course.referentialBookSeparator
import ru.otus.algorithms.work.course.resourcesPath
import java.io.File
import java.io.FileReader
import java.io.FileWriter

@Component
class ReferenceBookFileRepository : ReferenceBookRepository {

    private var referentialBook: Trie
    private var cache: Cache<Long, Trie> = QueuedLRUCache()
    private val referentialBookContent: List<String>

    init {
        val file = File(referentialBookPath)
        val reader = FileReader(file)
        referentialBookContent = reader.readLines()[0].split(referentialBookSeparator)
        val trie = Trie()
        trie.put(referentialBookContent)
        referentialBook = trie
    }

    override fun findByUserId(userId: Long): Trie {
        val cacheValue = cache.find(userId)
        if (cacheValue != null) return cacheValue
        val path = "$resourcesPath$userId-data.txt"
        val file = File(path)
        if (!file.exists()) return referentialBook
        val list = FileReader(file).readLines()[0].split(referentialBookSeparator)
        val words = ArrayList<String>(list)
        words.addAll(referentialBookContent)
        val trie = Trie()
        trie.put(words)
        println(words)
        cache = QueuedLRUCache()
        return trie
    }

    override fun systemSave(content: List<String>) {
        val array = prepareContentArray(content)
        val file = File(referentialBookPath)
        file.delete()
        file.createNewFile()
        val writer = FileWriter(file)
        writer.write(array.joinToString(referentialBookSeparator))
        writer.close()

        val trie = Trie()
        trie.put(array)

        referentialBook = trie
        cache = QueuedLRUCache()
    }

    override fun save(userId: Long, content: List<String>) {
        val array = prepareContentArray(content)
        val path = "$resourcesPath$userId-data.txt"
        val file = File(path)
        file.delete()
        file.createNewFile()
        val writer = FileWriter(file)
        writer.write(array.joinToString(referentialBookSeparator))
        writer.close()

        val cacheValue = cache.find(userId)
        if (cacheValue != null) cache = QueuedLRUCache()
    }

    private fun prepareContentArray(content: List<String>): Array<String> = content.stream()
            .flatMap { it.split("\\p{Punct}+".toRegex()).stream() }
            .map { it.replace(Char(160), ' ') }
            .flatMap { it.split("\\s".toRegex()).stream() }
            .map { it.replace("[»«\\t\\s]".toRegex(), "") }
            .filter { it.contains("\\D".toRegex()) }
            .map { it.replace("\\d".toRegex(), "") }
            .map { it.lowercase() }
            .filter { it.length > 3 } // подсказки даются от трех букв
            .toArray { length -> arrayOfNulls(length) }
}