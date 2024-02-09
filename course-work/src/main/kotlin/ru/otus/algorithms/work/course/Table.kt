package ru.otus.algorithms.work.course

interface Table<K, V> {

    fun size(): Int
    fun put(keyword: K): V
    fun getHashcode(key: K): Int {
        return key.hashCode()
    }

    fun getFrequency(key: K): V?
    fun contains(key: K): Boolean
}
