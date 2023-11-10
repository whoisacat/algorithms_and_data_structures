package ru.otus.algorithms.bst.avl

import java.lang.Exception

open class AvlTree {

    private var root: AVLNode? = null

    fun getRootElement(): AVLNode? {
        return root
    }

    private fun smallRightRotation(up: AVLNode): AVLNode {
        val down = up.left ?: throw Exception("cant do smallRightRotation without left child")
        val center = down.right

        up.left = null
        down.right = null

        down.right = up
        up.left = center

        return down
    }
    private fun smallLeftRotation(up: AVLNode): AVLNode {

        val down = up.right ?: throw Exception("cant do smallLeftRotation without right child")
        val center = down.left

        up.right = null
        down.left = null

        down.left = up
        up.right = center

        return down
    }

    private fun bigLeftRotation(node: AVLNode): AVLNode {
        val rightRotated = smallRightRotation(node.right
            ?: throw Exception("cant do bigRightRotation without right child"))
        node.right = rightRotated
        node.right?.right?.updateHeight()
        val rotated = smallLeftRotation(node)
        rotated.left?.updateHeight()
        return rotated
    }

    private fun bigRightRotation(node: AVLNode): AVLNode {
        val leftRotated = smallLeftRotation(node.left
            ?: throw Exception("cant do bigRightRotation without left child"))
        node.left = leftRotated
        node.left?.left?.updateHeight()
        val rotated = smallRightRotation(node)
        rotated.right?.updateHeight()
        return rotated
    }

    fun insert(key: Int, node: AVLNode? = root, parent: AVLNode? = null): AVLNode {
        if (node == null) {
            return if (root == null) {
                root = AVLNode(key, null)
                root!!
            } else {
                AVLNode(key, parent)
            }
        }
        if (key < node.key) {
            node.left = insert(key, node.left, node)
            if (node.left?.height == 1) node.left?.updateHeight()
        }
        if (key > node.key) {
            node.right = insert(key, node.right, node)
            if (node.right?.height == 1) node.right?.updateHeight()
        }
        if (node.key == key) println("replace data key = $key")
        return rotate(node)
    }

    private fun rotate(node: AVLNode): AVLNode {
        val dif = (node.left?.height ?: 0) - (node.right?.height ?: 0)
        if (dif <= 1 && dif >= -1) return node
        val parent = node.parent
        if (parent != null) {
            if (node.key < parent.key) {
                parent.left = null
            } else {
                parent.right = null
            }
        }
        val rotated = if (dif > 1)
            rotateToRight(node)
        else rotateToLeft(node)
        if (parent == null && node == root) root = rotated
        else if (rotated.key < parent!!.key) parent.left = rotated
        else parent.right = rotated
        rotated.left?.updateHeight()
        rotated.right?.updateHeight()
        return rotated
    }

    private fun rotateToRight(up: AVLNode): AVLNode {
        return if ((up.left?.left?.height ?: 0) >= (up.left?.right?.height ?: 0)) {
            smallRightRotation(up)
        } else {
            bigRightRotation(up)
        }
    }

    private fun rotateToLeft(up: AVLNode): AVLNode {
        return if ((up.right?.left?.height ?: 0) <= (up.right?.right?.height ?: 0)) {
            smallLeftRotation(up)
        } else {
            bigLeftRotation(up)
        }
    }

    fun search(key: Int, node: AVLNode? = null): Boolean {
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

    fun remove(key: Int, node: AVLNode? = null) {
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
            }
            child.updateHeight()
            return
        }
        makeMaxLeft(node)
    }

    private fun makeMaxLeft(node: AVLNode) {
        val replacer = getMaxLeft(node)
        remove(replacer.key, replacer)
        replaceOnlyNode(replacer, node)
        replacer.updateHeight()
    }

    private fun getMaxLeft(node: AVLNode): AVLNode {
        var max = node.left!!
        while (max.right != null) {
            max = max.right!!
        }
        return max
    }

    private fun replaceOnlyNode(replacer: AVLNode, place: AVLNode) {
        val rootNode = place.parent
        if (rootNode == null)
            root = replacer
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

    private fun makeNull(node: AVLNode) {
        if (node.parent?.left == node) node.parent?.left = null
        if (node.parent?.right == node) node.parent?.right = null
        node.parent?.updateHeight()
    }
}
