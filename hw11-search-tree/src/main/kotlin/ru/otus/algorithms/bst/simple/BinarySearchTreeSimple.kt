package ru.otus.algorithms.bst.simple

import java.lang.Exception

class BinarySearchTreeSimple() {

    private var root: Node? = null

    fun getRootElement(): Node? {
        return root
    }

    fun insert(key: Int, node: Node? = root): Node {
        if (node == null) {
            if (root == null) {
                root = Node(key)
                return root!!
            } else {
                return Node(key)
            }
        }
        if (key < node.key) node.left = insert(key, node.left)
        if (key > node.key) node.right = insert(key, node.right)
        if (node.key == key) println("replace data key = $key")
        return node
    }

    fun search(key: Int, node: Node? = null): Boolean {
        if (root == null) return false
        if (node == null) return search(key, root)
        if (key < node.key) return if (node.left == null) false else search(key, node.left)
        if (key > node.key) return if (node.right == null) false else search(key, node.right)
        return true
    }

    fun remove(key: Int, node: Node? = null) {
        if (node == null) return remove(key, root)
        if (key < node.key) return remove(key, node.left)
        if (key > node.key) return remove(key, node.right)
        if (node.left == null && node.right == null) makeNull(node)
        if (node.left == null && node.right != null
            || node.left != null && node.right == node) makeNode(node)
        makeMaxLeft(node)
    }

    private fun makeMaxLeft(node_: Node) {
        var node = node_
        var parentOfMaxLeft = node
        var right = parentOfMaxLeft.left ?: throw Exception("right must not be null after checking on null")
        while (right.right != null) {
            parentOfMaxLeft = right
            right = right.right ?: throw Exception("right must not be null after checking on null")
        }
        node = right
        parentOfMaxLeft.right = null

    }

    private fun makeNode(node_: Node) {
        var node = node_
        node = node_.left?:node_.right ?: throw Exception("right must not be null after checking on null")
    }

    private fun makeNull(node_: Node?) {
        var node = node_
        node = null
    }
}