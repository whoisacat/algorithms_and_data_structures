package ru.otus.algorithms.bst.random

import java.lang.Exception
import kotlin.random.Random

class RandTree {

    var root: RandNode? = null
    private var min: Int = 0
    private var max: Int = 0
    var size = 0

    fun insert(key: Int, node: RandNode? = root, parent: RandNode? = null): RandNode {

        if (node == null) {
            return if (root == null) {
                root = RandNode(key, null)
                min = key
                max = key
                size++
                root!!
            } else {
                size++
                RandNode(key, parent)
            }
        }
        if (key < node.key) {
            node.left = insert(key, node.left, node)
        } else if (key > node.key) {
            node.right = insert(key, node.right, node)
        } else println("replace data key = $key")
        return if (Random.nextInt() % (size + 1) == 0) down(node, key) else node
    }

    fun search(key: Int, node: RandNode? = null): Boolean {
        if (root == null) return false
        if (node == null)
            return search(key, root)
        if (key < node.key) {
            return if (node.left == null) false else search(key, node.left)
        }
        if (key > node.key) {
            return if (node.right == null) false else search(key, node.right)
        }
        return true
    }

    private fun down(node: RandNode, key: Int): RandNode {
        val parent = node.parent
        if (parent != null) {
            if (node.key < parent.key) {
                parent.left = null
            } else {
                parent.right = null
            }
        }
        val rotated = if (key < node.key) {
            smallRightRotation(node)
        } else {
            smallLeftRotation(node)
        }
        if (parent == null && node == root) root = rotated
        else if (rotated.key < parent!!.key) parent.left = rotated
        else parent.right = rotated
        return rotated
    }

    private fun smallRightRotation(up: RandNode): RandNode {
        val down = up.left ?: throw Exception("cant do smallRightRotation without left child")
        val center = down.right

        up.left = null
        down.right = null

        down.right = up
        up.left = center

        return down
    }

    private fun smallLeftRotation(up: RandNode): RandNode {

        val down = up.right ?: throw Exception("cant do smallLeftRotation without right child")
        val center = down.left

        up.right = null
        down.left = null

        down.left = up
        up.right = center

        return down
    }


    fun remove(key: Int, node: RandNode? = null) {
        if (node == null) return remove(key, root)
        if (key < node.key) {
            if (node.left == null) throw Exception("has no key $key")
            return remove(key, node.left)
        }
        if (key > node.key) {
            if (node.right == null) throw Exception("has no key $key")
            return remove(key, node.right)
        }
        if (node.left == null && node.right == null) {
            size--
            return makeNull(node)
        }
        if (node.left == null || node.right == null) {
            val child = node.left?:node.right ?: throw Exception("one child was checked on null")
            size--
            if (node.parent != null) {
                if (node.parent?.left == node) {
                    node.parent?.left = child
                } else {
                    node.parent?.right = child
                }
            } else {
                root = child
                root!!.cleanParent()
            }
            return
        }
        size--
        makeMaxLeft(node)
    }

    private fun makeMaxLeft(node: RandNode) {
        val replacer = getMaxLeft(node)
        remove(replacer.key, replacer)
        replaceOnlyNode(replacer, node)
    }

    private fun getMaxLeft(node: RandNode): RandNode {
        var max = node.left!!
        while (max.right != null) {
            max = max.right!!
        }
        return max
    }

    private fun replaceOnlyNode(replacer: RandNode, place: RandNode) {
        val rootNode = place.parent
        if (rootNode == null) root = replacer
        else if (replacer.key < rootNode.key)
            rootNode.left = replacer
        else rootNode.right = replacer
        if (replacer.key != place.left?.key) {
            replacer.left = place.left
        }
        if (replacer.key != place.right?.key) {
            replacer.right = place.right
        }
    }

    private fun makeNull(node: RandNode) {
        if (node.parent?.left == node) node.parent?.left = null
        if (node.parent?.right == node) node.parent?.right = null
    }
}
