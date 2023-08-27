package ru.otus.algorithms.dynamicarrays

import com.whoisacat.edu.otus.algoriths.dynamicarrays.DynamicArray

class FactorArray <T>(private val factor: Int) : DynamicArray<T> {

    private var content: Array<Any?> = Array(0) {null}
    private var size = 0

    override fun add(item: T, index: Int) {
        if (index > content.size) throw Exception("unsupported")
        if (index >= content.size || index <= size - 1) {
            makePlace(index)
        }
        content[index] = item
        size++
    }

    private fun makePlace(index: Int) {
        if (size + 1 > content.size) {
            val copy = Array<Any?>(
                if (size + 1 > content.size) if (content.size != 0) content.size * factor else factor else content.size
            ) { null }
            if (index == content.size) {
                System.arraycopy(content, 0, copy, 0, content.size)
            } else {
                System.arraycopy(content, 0, copy, 0, index)
                System.arraycopy(content, index, copy, index + 1, size - index)
            }
            content = copy
        } else {
            System.arraycopy(content, index, content, index + 1, size - index)
        }
    }

    override fun remove(index: Int): T? {
        if (index < 0) return null
        if (index > content.size) throw Exception("unsupported")
        val item = content[index]

        if (size <= content.size / factor) {
            val copy = Array<Any?>((content.size / factor)) { null }
            System.arraycopy(content, index + 1, content, index, size - (index + 1))
            content = copy
        } else {
            if (index == content.size - 1) {
                content[index] = null
            } else {
                System.arraycopy(content, index + 1, content, index, content.size - (index + 1) - calcEmpties())
            }
        }
        size--
        @Suppress("UNCHECKED_CAST")
        return item as T
    }

    private fun calcEmpties() = content.size - size

    override fun size(): Int {
        return size
    }
}