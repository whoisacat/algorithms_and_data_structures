package ru.otus.algorithms.work.course.domain.hash

interface Cache<K, V> {

    fun cache(key: K, value: V): V
    fun find(key: K): V?
}
