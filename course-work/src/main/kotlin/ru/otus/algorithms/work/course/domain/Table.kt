package ru.otus.algorithms.work.course.domain

interface Table<K, V> {

    fun size(): Int
    fun put(keywords: List<K>)
    fun put(keywords: Array<K>)
    fun getFrequency(key: K): V?
    fun contains(key: K): Boolean
    fun search(word: String): Boolean
    fun getWord(key: String): String?
    fun startsWith(prefix: String): Boolean
    fun getNextLetterWordsInDepth(depth: Byte): Array<String>
    fun getNextLetterWords(keyword: String = ""): Array<String>
}
