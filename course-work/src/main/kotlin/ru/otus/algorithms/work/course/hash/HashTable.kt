package ru.otus.algorithms.work.course.hash

import org.jetbrains.annotations.NotNull
import java.lang.Exception
import java.util.ArrayList
import java.util.NoSuchElementException
import java.util.stream.Collectors

open class HashTable<K, V>(private val maxLoadFactor: Double = 0.75, private val minLoadFactor: Double = 0.2) {


    private var loadFactor: Double = 0.0
    private var buckets: Array<HashTableNode<K, V>?> = Array(2) {null}
    private var size: Int = 0

    init {
        if (minLoadFactor >= maxLoadFactor) throw Exception("min load factor mast be less then max load factor")
    }
    fun size(): Int {
        return this.size
    }

    operator fun set(key: K, value: V) {
        put(key, value)
    }

    open fun put(key: K, value: V): V {
        if (!this.contains(key)) {
            if (loadFactor > maxLoadFactor)
                rehash()
            val hashTableNode = HashTableNode(key = key, value = value)
            placeNodeToBucket(hashTableNode)
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

    private fun placeNodeToBucket(hashTableNode: HashTableNode<K, V>) {
        val bucket = getBucketNumber(hashTableNode.key)
        if (buckets[bucket] == null) buckets[bucket] = hashTableNode
        else buckets[bucket]!!.put(hashTableNode)
    }

    private fun refreshLoadFactor() {
        this.loadFactor = this.size / buckets.size.toDouble()
    }

    private fun getBucketNumber(key: K): Int {
        val hashcode: Int = this.getHashcode(key)
        return hashcode % buckets.size
    }

    open fun getHashcode(key: K): Int {
        return key.hashCode()
    }

    open operator fun get(key: K): V? {
        val bucket = getBucketNumber(key)
        var hashTableNode: HashTableNode<K, V> = buckets[bucket] ?: return null
        while (hashTableNode.key != key && hashTableNode.next != null) hashTableNode = hashTableNode.next!!
        return hashTableNode.value
    }

    fun contains(key: K): Boolean {
        val bucket = getBucketNumber(key)
        var hashTableNode: HashTableNode<K, V> = buckets[bucket] ?: return false
        while (hashTableNode.key != key && hashTableNode.next != null) hashTableNode = hashTableNode.next!!
        return hashTableNode.key?.equals(key) ?: false
    }

    open fun delete(key: K) {
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
        val list = Array<HashTableNode<K, V>?>(this.size) {null}
        var i = 0
        for (bucket in buckets) {
            if (bucket == null) continue
            var node = bucket
            list[i++] = node
            while (node?.next != null) {
                node = node.next
                list[i++] = node
            }
        }
        for (node in list) {
            node?.next = null
        }
        this.buckets = Array((buckets.size * coefficient).toInt()) {null}
        for (node in list) {
            placeNodeToBucket(node!!)
        }
    }

    fun contentEquals(other: HashTable<K, V>): Boolean {
        return buckets.contentEquals(other.buckets)
    }

    fun contentHashCode(): Int {
        return buckets.contentHashCode()
    }

    override fun toString(): String {
        return "{table size: $size buckets ${buckets.size}:" +
                "${buckets.asList().stream().filter { it != null }.map { it.toString() }.collect(Collectors.joining(", "))}}"
    }

    fun valueContent(): List<V> {
        return content { it.value }
    }

    private fun <D> content(invocation: (HashTableNode<K, V>) -> D): List<D> {
        val content = ArrayList<D>()
        for (node in buckets) {
            if (node?.value != null) {
                content.add(invocation.invoke(node))
            }
            var next = node?.next
            while (next != null) {
                content.add(invocation.invoke(next))
                next = next.next
            }
        }
        return content
    }

    data class KeyValue<K, V>(val key: K, val value: V)

    fun keyValueContent(): List<KeyValue<K, V>> {
        return content { KeyValue(it.key, it.value) }
    }
}

private class HashTableNode<K, V>(@NotNull val key: K, var value: V, var next: HashTableNode<K, V>? = null) {

    init {
        if (key == null) throw Exception("key must not be null")
    }
    @Synchronized fun put(hashTableNode: HashTableNode<K, V>) {
        if (next == null) next = hashTableNode
        else next!!.put(hashTableNode)
    }

    override fun toString(): String {
        return "{hash table node key: $key next: $next value: $value}"
    }
}
