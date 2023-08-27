package ru.otus.algorithms.dynamicarrays

import java.util.LinkedList
import java.util.Queue

class ArrayPriorityQueue<T> {

    private var queues: Array<FactorArray<T>> = Array(0) { FactorArray(3) }

    fun enqueue(priority: Int, item: T) {
        if (queues.size <= priority) {
            val copy: Array<FactorArray<T>> = Array(priority + 1) { FactorArray(3) }
            for (i in queues.indices) {
                copy[i] = queues[i]
            }
            queues = copy
        }
        queues[priority].add(item, 0)
    }

    fun dequeue(): T? {
        for (q in queues) {
            return q.remove(q.size() - 1) ?: continue
        }
        return null
    }
}