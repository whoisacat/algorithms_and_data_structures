package ru.otus.algorithms.work.course.persistence

import org.springframework.stereotype.Component
import ru.otus.algorithms.work.course.Trie
import ru.otus.algorithms.work.course.hash.LRUCache
import ru.otus.algorithms.work.course.referentialBookPath
import ru.otus.algorithms.work.course.referentialBookSeparator
import ru.otus.algorithms.work.course.resourcesPath
import java.io.File
import java.io.FileReader

@Component
class ReferenceBookFileRepository : ReferenceBookRepository {

    private val referentialBook: Trie
    private val cache: LRUCache<Long, Trie> = LRUCache()

    init {
        val file = File(referentialBookPath)
        val reader = FileReader(file)
        val words = reader.readLines()[0].split(referentialBookSeparator)
        val trie = Trie()
        for (word in words) {
            trie.put(word)
        }
        referentialBook = trie
    }

    override fun findByUserId(userId: Long): Trie {
        val cacheValue = cache[userId]
        if (cacheValue?.value != null) return cacheValue.value
        val path = "$resourcesPath$userId-data.txt"
        val file = File(path)
        if (!file.exists()) return referentialBook
        val array = FileReader(file).readLines()[0].split(referentialBookSeparator)
        val trie = Trie()
        for (word in array) trie.put(word)
        cache.put(userId, trie)
        return trie
    }
}