package ru.otus.algorithms.hash.trie

interface Table<K, V> {

    fun size(): Int
    fun put(key: K, value: V): V
    fun getHashcode(key: K): Int {
        return key.hashCode()
    }

    fun get(key: K): V?
    fun contains(key: K): Boolean
    fun delete(key: K)
}
