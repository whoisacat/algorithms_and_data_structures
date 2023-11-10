package ru.otus.algorithms.bst.splay

import java.lang.Exception

class SplayNode(val key: Int,
                parent: SplayNode?,
                left: SplayNode? = null,
                right: SplayNode? = null) {

    var parent: SplayNode? = parent
        private set(value) {
            field = value
            if (parent?.key == key) throw Exception("self parentnes")
        }
        get() = field

    var left: SplayNode? = left
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

    var right: SplayNode? = right
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

    private fun validateState() {
        if (this.key == parent?.key) throw Exception("parent key must be different")
        if (this.key == left?.key) throw Exception("left key must be different")
        if (this.key == right?.key) throw Exception("right key must be different")
        if ((parent != null || left != null) && parent?.key == left?.key) throw Exception("parent key and left key can't be the same")
        if ((parent != null || right != null) && parent?.key == right?.key) throw Exception("parent key and right key can't be the same")
    }

    override fun toString(): String {
        return "{$key,parent=${parent?.key ?: ".."},left=${left?.key ?: ".."},right=${right?.key ?: ".."}}"
    }

    fun cleanParent() {
        this.parent = null
    }
}
