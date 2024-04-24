package ru.otus.algorithms.work.course.domain


class FactorArray (private val factor: Int) {

    private var content: Array<String?> = Array(0) {null}
    private var size = 0

    fun add(item: String, index: Int = size) {
        if (index > content.size) throw Exception("unsupported")
        if (index >= content.size || index <= size - 1) {
            makePlace(index)
        }
        content[index] = item
        size++
    }

    private fun makePlace(index: Int) {
        if (size + 1 > content.size) {
            val copy = Array<String?>(
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

    fun remove(index: Int): String? {
        if (index < 0) return null
        if (index > content.size) throw Exception("unsupported")
        val item = content[index]

        if (size <= content.size / factor) {
            val copy = Array<String?>((content.size / factor)) { null }
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
        return item as String
    }

    private fun calcEmpties() = content.size - size

    fun size(): Int {
        return size
    }

    private fun trim() {
        val copy = Array<String?>(size) {null}
        System.arraycopy(content, 0, copy, 0, size)
        content = copy
    }

    fun getContent(): Array<String> {
        trim()
        @Suppress("UNCHECKED_CAST")
        return content as Array<String>
    }

    fun addAll(array: Array<String>) {
        val new = Array<String?>(array.size + size) {null}
        System.arraycopy(content, 0, new, 0, size)
        System.arraycopy(array, 0, new, size, array.size)
        content = new
        size += array.size
    }
}
