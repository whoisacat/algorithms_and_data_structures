package ru.otus.algorithms.bst.avl

import java.lang.Exception

class AVLNode(val key: Int,
              parent: AVLNode?,
              left: AVLNode? = null,
              right: AVLNode? = null,
              var height: Int = 1) {

    var parent: AVLNode? = parent
        private set(value) {
            field = value
            if (parent?.key == key) throw Exception("self parentnes")
        }
        get() = field


    var left: AVLNode? = left
        set(value) {
            if (value != null) {
                field = value
                value.parent = this
            } else {
                field?.parent = value
                field = value
            }
            validateState()
        }

    var right: AVLNode? = right
        set(value) {
            if (value != null) {
                field = value
                value.parent = this
            } else {
                field?.parent = null
                field = value
            }
            validateState()
        }

    init {
        this.parent = parent
        validateState()
    }

    fun updateHeight() {
        this.height =
            if (left == null && right == null) 1
            else if ((left?.height ?: 0) > (right?.height ?: 0)) (left?.height?:0) + 1
            else (right?.height ?: 0) + 1
        this.parent?.updateHeight()
    }

    private fun validateState() {
        if (this.key == parent?.key) throw Exception("parent key must be different")
        if (this.key == left?.key) throw Exception("left key must be different")
        if (this.key == right?.key) throw Exception("right key must be different")
        if ((parent != null || left != null) && parent?.key == left?.key) throw Exception("parent key and left key can't be the same")
        if ((parent != null || right != null) && parent?.key == right?.key) throw Exception("parent key and right key can't be the same")
    }

    override fun toString(): String {
        return "{$key,height=$height,parent=${parent?.key ?: ".."},left=${left?.key ?: ".."},right=${right?.key ?: ".."}}"
    }
}
