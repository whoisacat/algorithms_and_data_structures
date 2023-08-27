package ru.otus.algorithms.dynamicarrays

import com.whoisacat.edu.otus.algoriths.dynamicarrays.DynamicArray

class VectorArray <T>(val vector: Int): DynamicArray<T> {

    private var content: Array<Any?> = Array(vector) {null}
    private var size: Int = 0

    override fun add(item: T, index: Int) {
        if (index > content.size) throw Exception("unsupported")
        if (index >= content.size || index <= size - 1) {
            makePlace(index)
        }
        content[index] = item
        size++
    }

    private fun makePlace(index: Int) {
        val copy = Array<Any?>(if (size + 1 > content.size) content.size + vector else content.size) {null}
        if (index == content.size) {
            System.arraycopy(content, 0, copy, 0, content.size)
        } else if (index != 0){
            System.arraycopy(content, 0, copy, 0, index)
            System.arraycopy(content, index, copy, index + 1, size - index)
        } else {
            System.arraycopy(content, 0, copy, 1, size)
        }
        content = copy
    }

    override fun remove(index: Int): T {
        if (index > content.size) throw Exception("unsupported")
        val item = content[index]
        val copySize = if (size - 1 <= content.size - vector) content.size - vector else content.size
        val copy = Array<Any?>((copySize)) {null}
        if (index == content.size - 1) {
            System.arraycopy(content, 0, copy, 0, content.size - 1)
        } else if (index == 0) {
            System.arraycopy(content, 1, copy, 0, size - 1)
        } else {
            System.arraycopy(content, 0, copy, 0, index)
            System.arraycopy(content, index + 1, copy, 0, size - (index + 1))
        }
        content = copy
        size--
        @Suppress("UNCHECKED_CAST")
        return item as T
    }

    override fun size(): Int {
        return size
    }
}