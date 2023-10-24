package ru.otus.algorithms.hash.first

import org.jetbrains.annotations.NotNull
import java.lang.Exception

open class LinearProbingHashTable<K, V> (
    private val maxLoadFactor: Double = 0.75,
    private val minLoadFactor: Double = 0.2,
    private val maxCollisions: Int = 10
    ): Table<K, V> {


    private var loadFactor: Double = 0.0;
    private var items: Array<Item<K, V>?> = Array(10) {null}
    private var size: Int = 0

    init {
        if (minLoadFactor >= maxLoadFactor) throw Exception("min load factor mast be less then max load factor")
    }
    override fun size(): Int {
        return this.size;
    }

    @Synchronized
    override fun put(key: K, value: V): V {
        if (loadFactor > maxLoadFactor) {
            var i = 2.0
            while (!rehash(i)) i++
        }
        val idx = findItemIndex(key)
        if (items[idx] == null) {
            items[idx] = Item(key = key, value = value)
            size++
            refreshLoadFactor()
            return value
        } else {
            return if (items[idx]!!.key == key) {
                val previous = items[idx]!!.value
                items[idx]!!.value = value
                previous
            } else {
                var i = 2.0
                while (!rehash(i)) i++
                put(key = key, value = value)
            }
        }
    }

    private fun refreshLoadFactor() {
        this.loadFactor = this.size / items.size.toDouble()
    }

    override fun get(key: K): V? {
        val item = items[findItemIndex(key)]
        if (item == null || item.deleted) return null
        return item.value
    }

    private fun getBucketNumber(key: K, i: Int): Int {
        val hashCode = key.hashCode()
        return ((if (hashCode < 0) hashCode * -1 else hashCode) % items.size + i) % items.size
    }

    override fun contains(key: K): Boolean {
        val idx: Int = findItemIndex(key)
        return items[idx] != null && !items[idx]!!.deleted
    }

    private fun findItemIndex(key: K): Int {
        var i = 0
        var idx: Int
        do {
            idx = getBucketNumber(key, i++)
        } while (i < maxCollisions && items[idx] != null && items[idx]?.key != key)
        // todo Не кажется ли вам, что постоянный вызов (и расчёт!) хэш-функции внутри этого цикла снижает эффективность?
        // Придумайте вариант оптимизации, который бы также расчитывал функцию пробинга, но делал это без лишних расчётов хэш-функций ключа. Хотя, может котлин это оптимизирует, не знаю. Жду ответа.
        return idx
    }

    @Synchronized
    override fun delete(key: K) {
        val idx = findItemIndex(key)
        if (items[idx] != null && items[idx]!!.key == key) {
            items[idx]!!.deleted = true
            refreshLoadFactor()
            size--
        } else throw Exception("findItemIndex(key) in delete reterns wrong idx")
        if (loadFactor < minLoadFactor) rehash(0.5)
    }

    private fun rehash(coefficient: Double = 2.0): Boolean {
        val prev = items
        items = Array((items.size * coefficient).toInt()) {null}
        for (item in prev) {
            if (item == null || item.deleted) continue
            val idx = findItemIndex(item.key)
            if (items[idx] == null) {
                items[idx] = item
            } else {
                items = prev
                return false
            }
        }
        refreshLoadFactor()
        return true
    }
}

class Item<K, V>(@NotNull val key: K, var value: V, var deleted: Boolean = false) {

    init {
        if (key == null) throw Exception("key must not be null")
        if (deleted) throw Exception("cant create deleted item")
    }
}
