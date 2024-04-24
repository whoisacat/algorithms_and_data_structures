package ru.otus.algorithms.work.course.domain.hash

import java.lang.Exception
import java.lang.RuntimeException
import javax.naming.OperationNotSupportedException

class QueuedLRUCache<K, V>(val cacheSize: Int = 10):
    HashTable<K, QueuedLRUCache.KeyValueNode<K, V>>(maxLoadFactor = 1.0, minLoadFactor = 1 / cacheSize.toDouble()),
    Cache<K, V> {

    val lock = Any()
    var firstNode: KeyValueNode<K, V>? = null
    var lastNode: KeyValueNode<K, V>? = null

    data class KeyValueNode <K, V>(val key: K, val value: V,
                                   private var next: KeyValueNode<K, V>? = null,
                                   private var prev: KeyValueNode<K, V>? = null) {
        private var size: Int
        init {
            if (key == null) throw Exception("key must not be null")
            size = 1
        }

        @Synchronized fun put(node: KeyValueNode<K, V>) {
            node.next = this
            this.prev = node
            size++
            node.size = size
        }

        fun eject(): KeyValueNode<K, V> {
            if (prev == null) throw RuntimeException("cent eject first node")
            prev?.next = next
            return prev!!
        }

        fun promote(lastNode: KeyValueNode<K, V>): KeyValueNode<K, V> {
            if (prev == null) return lastNode
            prev!!.next = next
            var first = prev
            while (first!!.prev != null) first = first.prev
            val isLast = this.next == null
            if (isLast) {
                val newLastNode = this.prev
                this.prev = null
                this.next = first
                return if (size > 1) newLastNode!! else this
            }
            this.prev = null
            return lastNode
        }
    }

    override fun delete(key: K) {
        throw OperationNotSupportedException()
    }

    override operator fun get(key: K): KeyValueNode<K, V>? {
        throw OperationNotSupportedException("use find")
    }

    override operator fun set(key: K, value: KeyValueNode<K, V>) {
        cache(key, value.value)
    }

    override fun put(key: K, value: KeyValueNode<K, V>): KeyValueNode<K, V> {
        throw OperationNotSupportedException()
    }

    override fun cache(key: K, value: V): V {
        val existing = find(key)
        if (existing != null) {
            return if (existing == value) {
                existing
            } else {
                super.put(key, KeyValueNode(key, value)).value
            }
        } else {
            val node = KeyValueNode(key, value)
            synchronized(lock) {
                if (firstNode == null) {
                    firstNode = node
                    lastNode = firstNode
                } else {
                    firstNode!!.put(node)
                    firstNode = node
                    if (size() >= cacheSize) {
                        super.delete(lastNode!!.key)
                        lastNode = lastNode!!.eject()
                    }
                }
                super.put(key, node)
            }
            return node.value
        }
    }

    override fun find(key: K): V? {
        if (firstNode == null) return null
        val v = super.get(key)
        if (v?.value == null) return null
        firstNode = v
        lastNode = v.promote(lastNode!!)
        return v.value
    }
}