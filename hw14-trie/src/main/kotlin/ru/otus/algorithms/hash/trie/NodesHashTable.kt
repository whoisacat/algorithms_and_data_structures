package ru.otus.algorithms.hash.trie

import org.jetbrains.annotations.NotNull
import java.lang.Exception
import java.util.NoSuchElementException

open class NodesHashTable<K, V> (
    private val maxLoadFactor: Double = 0.75, private val minLoadFactor: Double = 0.2): Table<K, V> {


    private var loadFactor: Double = 0.0;
    private var buckets: Array<Node<K, V>?> = Array(10) {null}
    private var size: Int = 0

    init {
        if (minLoadFactor >= maxLoadFactor) throw Exception("min load factor mast be less then max load factor")
    }
    override fun size(): Int {
        return this.size;
    }

    @Synchronized
    override fun put(key: K, value: V): V {
        if (!this.contains(key)) {
            if (loadFactor > maxLoadFactor)
                rehash()
            val node = Node(key = key, value = value)
            placeNodeToBucket(node)
            size++
            refreshLoadFactor()
            return value
        } else {
            val bucket = getBucketNumber(key)
            var node = buckets[bucket]
            while (node?.key != key) node = node?.next ?: throw Exception("concurrency occasion")
            val previous = node?.value ?: throw Exception("concurrency occasion")
            node.value = value
            return previous
        }

    }

    private fun placeNodeToBucket(node: Node<K, V>) {
        val bucket = getBucketNumber(node.key)
        if (buckets[bucket] == null) buckets[bucket] = node
        else buckets[bucket]!!.put(node)
    }

    private fun refreshLoadFactor() {
        this.loadFactor = this.size / buckets.size.toDouble()
    }

    private fun getBucketNumber(key: K): Int {
        val hashcode: Int = this.getHashcode(key)
        return hashcode % buckets.size
    }

    override fun get(key: K): V? {
        val bucket = getBucketNumber(key)
        var node: Node<K, V> = buckets[bucket] ?: return null
        while (node.key != key && node.next != null) node = node.next!!
        return node.value
    }

    override fun contains(key: K): Boolean {
        val bucket = getBucketNumber(key)
        var node: Node<K, V> = buckets[bucket] ?: return false
        while (node.key != key && node.next != null) node = node.next!!
        return node.key?.equals(key) ?: false
    }

    @Synchronized
    override fun delete(key: K) {
        if (!contains(key)) throw NoSuchElementException()
        val bucket = getBucketNumber(key)
        if (buckets[bucket]!!.key!! == key) {
            buckets[bucket] = buckets[bucket]!!.next
        } else {
            var parent = buckets[bucket]
            while (parent!!.next!!.key != key) {
                parent = parent.next
            }
            parent.next = parent.next!!.next
        }
        size--
        refreshLoadFactor()
        if (this.loadFactor < this.minLoadFactor && buckets.size > 10)
            rehash(0.5)
    }

    private fun rehash(coefficient: Double = 2.0) {
        val nodes = Array<Node<K, V>?>(this.size) {null}
        var i = 0
        for (bucket in buckets) {
            if (bucket == null) continue
            var node = bucket
            nodes[i++] = node
            while (node?.next != null) {
                node = node.next
                nodes[i++] = node
            }
        }
        for (node in nodes) {
            node?.next = null
        }
        this.buckets = Array((buckets.size * coefficient).toInt()) {null}
        for (node in nodes) {
            placeNodeToBucket(node!!)
        }
    }
}

class Node<K, V>(@NotNull val key: K, var value: V, var next: Node<K, V>? = null) {

    init {
        if (key == null) throw Exception("key must not be null")
    }
    @Synchronized fun put(node: Node<K, V>) {
        if (next == null) next = node
        else next!!.put(node)
    }

}
