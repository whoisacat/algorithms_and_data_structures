package ru.otus.algorithms.work.course

import ru.otus.algorithms.work.course.hash.HashTable
import java.lang.RuntimeException
import java.util.function.Supplier

class Trie(): Table<String, Int>  {

    companion object {
        const val MAX_DEPTH: Byte = 6
    }

    private var root: Node = Node()
    private var size = 0

    override fun put(keyword: String): Int {
        val frequency = root.place(keyword)
        if (frequency == 1) size++
        root.updateWord(keyword, keyword, frequency)
        return frequency
    }

    fun getNextLetterWords(keyword: String = ""): Array<String> {
        val node = findNode(keyword)
        val factorArray = FactorArray(4)
        if (node.isValuable) factorArray.add(keyword)
        for (child in node.children.valueContent()) {
            if (child?.word != null) factorArray.add(child.word!!)
        }
        val content = factorArray.getContent()
        quickSort(content)
        return content
    }

    fun getNextLetterWordsInDepth(depth: Byte): Array<String> {
        if (depth > MAX_DEPTH) throw RuntimeException("max depth is $MAX_DEPTH")
        if (depth < 1) throw RuntimeException("min depth is 1")
        var array: Array<String> = arrayOf("")
        for (i in 0 .. depth) {
            val tmp = FactorArray(2)
            for (string in array) {
                val array1 = getNextLetterWords(string.substring(0, if (i > string.length) string.length else i))
                tmp.addAll(array1)
            }
            array = tmp.getContent()
        }
        return array
    }

    private fun findNode(keyword: String): Node {
        if (keyword.isEmpty()) return root
        var node = root
        for (char in keyword) {
            node = node.children[char] ?: return root
        }
        return node
    }

    private fun quickSort(array: Array<String>, left: Int = 0, right: Int = array.size - 1) {
        if (left >= right) return
        val m = split(array, left, right)
        quickSort(array, left, m - 1)
        quickSort(array, m + 1, right)
    }

    private fun split(array: Array<String>, left: Int, right: Int): Int {
        val gauge = array[right]
        var m = left - 1
        for (j in left .. (right))
            if (array[j] <= gauge)
                swap(array, ++m, j)
        return m
    }

    private fun swap(array: Array<String>, a: Int, b: Int) {
        val tmp = array[a]
        array[a] = array[b]
        array[b] = tmp
    }

    fun search(word: String): Boolean {
        var node = root
        for (char in word) {
            if (node.children[char] == null) return false
            node = node.next(char) ?: return false
        }
        return node.isValuable
    }

    override fun getFrequency(key: String): Int? {
        var node = root
        for (char in key) {
            if (node.children[char] == null) return null
            node = node.next(char) ?: return null
        }
        return node.frequency
    }

    fun getWord(key: String): String? {
        var node = root
        for (char in key) {
            if (node.children[char] == null) return null
            node = node.next(char) ?: return null
        }
        return node.word
    }

    fun startsWith(prefix: String): Boolean {
        var node: Node? = root
        for (char in prefix) {
            node = node?.children?.get(char) ?: return false
        }
        return node != null
    }

    private data class Node(var frequency: Int = 0, var isValuable: Boolean = false) {
        var word: String? = null
        val children: HashTable<Char, Node?> = HashTable()

        fun place(keyword: String): Int {
            return if (keyword.length > 1) {
                nextOrNew(keyword, false) {
                    children[keyword[0]]!!.place(keyword.substring(1))
                }
            } else {
                nextOrNew(keyword, true) {
                    children[keyword[0]]!!.frequency
                }
            }
        }

        fun updateWord(keyword: CharSequence, word: String, frequency: Int) {
            if (this.frequency > frequency) {
                if (keyword.isEmpty()) return
                children[keyword[0]]!!.updateWord(keyword.substring(1), word, frequency)
            } else {
                this.word = word
                this.frequency = frequency
                if (keyword.isEmpty()) {
                    this.isValuable = true
                    return
                }
                children[keyword[0]]!!.updateWord(keyword.substring(1), word, frequency)
            }
        }

        private fun nextOrNew(keyword: String, isValuable: Boolean, supplier: Supplier<Int>): Int {
            var node: Node? = null
            if (children.contains(keyword[0])) node = children[keyword[0]]
            if (node == null) {
                children[keyword[0]] = Node(1, isValuable)
            } else {
                if (keyword.length == 1) node.frequency++
            }
            return supplier.get()
        }

        fun next(char: Char): Node? {
            return children[char]
        }


        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Node

            if (!children.contentEquals(other.children)) return false
            return isValuable == other.isValuable
        }

        override fun hashCode(): Int {
            var result = children.contentHashCode()
            result = 31 * result + isValuable.hashCode()
            return result
        }

        override fun toString(): String {
            return "{trie node frequence: $frequency isValuable: $isValuable word: $word children: $children}"
        }
    }


    override fun size(): Int {
        return this.size
    }

    override fun contains(key: String): Boolean {
        return search(key)
    }

    override fun toString(): String {
        return "{trie size: $size root: $root}"
    }
}
