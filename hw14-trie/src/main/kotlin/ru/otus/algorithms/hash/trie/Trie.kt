package ru.otus.algorithms.hash.trie

import java.lang.Exception
import java.util.Stack

class Trie(): Table<String, Int>  {

    private var root: Node = Node()
    private var size = 0

    fun insert(word: String, value: Int): Int {
        var node = root
        for (char in word) node = node.nextOrNew(char)
        node.isValuable = true
        if (node.value == null) {
            size++
        }
        val prev = node.value
        node.value = value
        return prev ?: value
    }

    fun search(word: String): Boolean {
        var node = root
        for (char in word) {
            if (node.children[idx(char)] == null) return false
            node = node.nextOrNew(char)
        }
        return node.isValuable
    }

    override fun get(key: String): Int? {
        var node = root
        for (char in key) {
            if (node.children[idx(char)] == null) return null
            node = node.nextOrNew(char)
        }
        return node.value
    }

    fun startsWith(prefix: String): Boolean {
        var node: Node? = root
        for (char in prefix) {
            node = node?.children?.get(idx(char)) ?: return false
        }
        return node != null
    }

    private data class Node(var value: Int? = null, var isValuable: Boolean = false) {
        val children: Array<Node?> = Array(MAX_CHAR.code - MIN_CHAR.code + 1) {null}

        fun nextOrNew(char: Char): Node {
            val idx = idx(char)
            if (children[idx] == null) children[idx] = Node()
            return children[idx]!!
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
    }

    companion object {
        private const val MIN_CHAR = 'a'
        private const val MAX_CHAR = 'z'
        @JvmStatic
        fun idx(char: Char): Int {
            return char.code - MIN_CHAR.code
        }
    }

    override fun size(): Int {
        return this.size
    }

    override fun delete(key: String) {
        val nodes: Stack<Node> = Stack()
        var node = root
        for (char in key) {
            val idx = idx(char)
            if (node.children[idx] == null) throw Exception("no such element")
            nodes.push(node)
            node = node.children[idx]!!
        }
        if (!node.isValuable) throw Exception("element is not valuable")
        node.value = null
        node = nodes.pop()
        size--
        var keyIdx = key.length - 1
        while (!nodes.empty()) {
            val parent = nodes.pop()
            if (node.isValuable) return
            parent.children[idx(key[keyIdx--])] = null
            node = parent
        }
    }

    override fun contains(key: String): Boolean {
        return search(key)
    }

    override fun put(key: String, value: Int): Int {
        return insert(key, value)
    }
}