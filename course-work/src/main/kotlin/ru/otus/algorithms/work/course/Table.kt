package ru.otus.algorithms.work.course

interface Table<K, V> {

    fun size(): Int
    fun put(keywords: List<K>)
    fun put(keywords: Array<K>)
    fun getFrequency(key: K): V?
    fun contains(key: K): Boolean
}
