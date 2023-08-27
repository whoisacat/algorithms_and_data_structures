package ru.otus.algorithms.dynamicarrays

import com.whoisacat.edu.otus.algoriths.dynamicarrays.DynamicArray

class SingleArray <T>: DynamicArray<T> {

    private var content: Array<Any?> = Array(0) {null}


    override fun add(item: T, index: Int) {
        if (index > content.size) throw Exception("unsupported")
        makePlace(index)
        content[index] = item
    }

    private fun makePlace(index: Int) {
        val copy = Array<Any?>(content.size + 1) {null}
        if (index == content.size) {
            System.arraycopy(content, 0, copy, 0, content.size)
        } else if (index != 0) {
            System.arraycopy(content, 0, copy, 0, index)
            System.arraycopy(content, index, copy, index + 1, content.size - index)
        } else {
            System.arraycopy(content, 0, copy, 1, content.size)
        }
        content = copy
    }

    override fun remove(index: Int): T {
        if (index > content.size) throw Exception("unsupported")
        val item = content[index]
        val copy = Array<Any?>(content.size - 1) {null}
        if (index == content.size - 1) {
            System.arraycopy(content, 0, copy, 0, content.size - 1)
        } else if (index == 0) {
            System.arraycopy(content, 1, copy, 0, content.size - 1)
        } else {
            System.arraycopy(content, 0, copy, 0, content.size - index - 1)
            System.arraycopy(content, index + 1, copy, 0, content.size - (index + 1))
        }
        content = copy
        @Suppress("UNCHECKED_CAST")
        return item as T
    }

    override fun size(): Int {
        return content.size
    }
}