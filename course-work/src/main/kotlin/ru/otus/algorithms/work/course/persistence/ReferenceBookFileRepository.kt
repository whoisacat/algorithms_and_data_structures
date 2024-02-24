package ru.otus.algorithms.work.course.persistence

import org.springframework.stereotype.Component
import ru.otus.algorithms.work.course.Trie
import ru.otus.algorithms.work.course.hash.Cache
import ru.otus.algorithms.work.course.hash.QueuedLRUCache
import ru.otus.algorithms.work.course.referentialBookPath
import ru.otus.algorithms.work.course.referentialBookSeparator
import ru.otus.algorithms.work.course.resourcesPath
import java.io.File
import java.io.FileReader

@Component
class ReferenceBookFileRepository : ReferenceBookRepository {

    private val referentialBook: Trie
    private val cache: Cache<Long, Trie> = QueuedLRUCache()

    init {
        val file = File(referentialBookPath)
        val reader = FileReader(file)
        val words = reader.readLines()[0].split(referentialBookSeparator)
        val trie = Trie()
        for (word in words) trie.put(word)
        referentialBook = trie
    }

    override fun findByUserId(userId: Long): Trie {
        val cacheValue = cache.find(userId)
        if (cacheValue != null) return cacheValue
        val path = "$resourcesPath$userId-data.txt"
        val file = File(path)
        if (!file.exists()) return referentialBook
        val array = FileReader(file).readLines()[0].split(referentialBookSeparator)
        val trie = referentialBook.copy()
        for (word in array) trie.put(word)
        cache.cache(userId, trie)
        return trie
    }
}