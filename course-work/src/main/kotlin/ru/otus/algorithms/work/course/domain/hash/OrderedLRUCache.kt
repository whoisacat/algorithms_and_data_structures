package ru.otus.algorithms.work.course.domain.hash

import javax.naming.OperationNotSupportedException

class OrderedLRUCache<K, V>(val cacheSize: Int = 10):
        HashTable<K, OrderedLRUCache.CacheValue<V>>(maxLoadFactor = 1.0, minLoadFactor = 1 / cacheSize.toDouble()),
    Cache<K, V> {

    data class CacheValue<CV>(var order: Int, val value: CV)

    var oldestOne: K? = null

    override fun put(key: K, value: CacheValue<V>): CacheValue<V> {
        throw OperationNotSupportedException()
    }

    override fun delete(key: K) {
        throw OperationNotSupportedException()
    }

    override operator fun get(key: K): CacheValue<V>? {
        val value = super.get(key)
        if (value == null) return value
        for (item in keyValueContent()) {
            if (item.value.order < value.order) {
                item.value.order += 1
            }
        }
        value.order = 1
        return value
    }

    @Synchronized override fun cache(key: K, value: V): V {
        if (!contains(key)) {
            if (size() == cacheSize) {
                this.delete(oldestOne!!)
            }
            for (item in this.keyValueContent()) {
                if (item.value.order == size()) oldestOne = item.key
                item.value.order += 1
            }
            return super.put(key, CacheValue(1, value)).value
        }
        val order = super.get(key)!!.order
        if (order > 1) {
            for (item in this.keyValueContent()) {
                if (item.value.order < order) {
                    item.value.order += 1
                }
            }
            super.get(key)!!.order = 1
        }
        return value
    }

    override fun find(key: K): V? {
        return get(key)?.value
    }
}