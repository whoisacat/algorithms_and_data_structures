package ru.otus.algorithms.bst.splay

import java.lang.Exception

class SplayTree {

    var root: SplayNode? = null
    private var min: Int = 0
    private var max: Int = 0

    fun insert(key: Int, node: SplayNode? = root, parent: SplayNode? = null): SplayNode {

        if (node == null) {
            return if (root == null) {
                root = SplayNode(key, null)
                min = key
                max = key
                root!!
            } else {
                SplayNode(key, parent)
            }
        }
        if (key < min) {
            min = key
            val r = root
            root = SplayNode(key, null)
            root!!.right = r
            return root!!
        }
        if (key > max) {
            max = key
            val r = root
            root = SplayNode(key, null)
            root!!.left = r
            return root!!
        }
        if (key < node.key) {
            node.left = insert(key, node.left, node)
        } else if (key > node.key) {
            node.right = insert(key, node.right, node)
        } else println("replace data key = $key")
        return down(node, key)
    }

    fun search(key: Int, node: SplayNode? = null): Boolean {
        if (root == null) return false
        if (node == null)
            return search(key, root)
        if (key < node.key) {
            return if (node.left == null) false else search(key, node.left)
        }
        if (key > node.key) {
            return if (node.right == null) false else search(key, node.right)
        }
        if (root != node) while (node.parent != null) down(node.parent!!, key)
        return true
    }

    private fun down(node: SplayNode, key: Int): SplayNode {
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

    private fun smallRightRotation(up: SplayNode): SplayNode {
        val down = up.left ?: throw Exception("cant do smallRightRotation without left child")
        val center = down.right

        up.left = null
        down.right = null

        down.right = up
        up.left = center

        return down
    }

    private fun smallLeftRotation(up: SplayNode): SplayNode {

        val down = up.right ?: throw Exception("cant do smallLeftRotation without right child")
        val center = down.left

        up.right = null
        down.left = null

        down.left = up
        up.right = center

        return down
    }


    fun remove(key: Int, node: SplayNode? = null) {
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
            return makeNull(node)
        }
        if (node.left == null || node.right == null) {
            val child = node.left?:node.right ?: throw Exception("one child was checked on null")
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
        makeMaxLeft(node)
    }

    private fun makeMaxLeft(node: SplayNode) {
        val replacer = getMaxLeft(node)
        remove(replacer.key, replacer)
        replaceOnlyNode(replacer, node)
    }

    private fun getMaxLeft(node: SplayNode): SplayNode {
        var max = node.left!!
        while (max.right != null) {
            max = max.right!!
        }
        return max
    }

    private fun replaceOnlyNode(replacer: SplayNode, place: SplayNode) {
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

    private fun makeNull(node: SplayNode) {
        if (node.parent?.left == node) node.parent?.left = null
        if (node.parent?.right == node) node.parent?.right = null
    }
}
