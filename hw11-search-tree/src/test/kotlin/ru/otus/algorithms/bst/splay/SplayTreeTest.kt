package ru.otus.algorithms.bst.splay

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.lang.Exception
import kotlin.random.Random

class SplayTreeTest {

    @Test
    fun insertOne() {
        val tree = SplayTree()

        tree.insert(10)

        assertEquals(10, tree.root!!.key)
    }

    @Test
    fun insertTwo() {
        val tree = SplayTree()

        tree.insert(10)
        tree.insert(20)

        assertEquals(20, tree.root!!.key)
        assertNull(tree.root!!.parent)
        assertEquals(10, tree.root!!.left!!.key)
    }

    @Test
    fun insertTree() {
        val tree = SplayTree()

        tree.insert(10)
        tree.insert(20)
        tree.insert(30)

        assertEquals(30, tree.root!!.key)
        assertEquals(20, tree.root!!.left!!.key)
        assertEquals(10, tree.root!!.left!!.left!!.key)
    }

    @Test
    fun insertTreeInTheMiddle() {
        val tree = SplayTree()

        tree.insert(10)
        tree.insert(20)
        tree.insert(15)

        assertEquals(15, tree.root!!.key)
        assertNull(tree.root!!.parent)
        assertEquals(10, tree.root!!.left!!.key)
        assertNull(tree.root!!.left!!.left)
        assertNull(tree.root!!.left!!.right)
        assertEquals(20, tree.root!!.right!!.key)
        assertNull(tree.root!!.right!!.left)
        assertNull(tree.root!!.right!!.right)
    }

    @Test
    fun searchTreeInTheMiddle() {
        val tree = SplayTree()
        tree.insert(10)
        tree.insert(20)
        tree.insert(15)

        assertTrue(tree.search(20))

        assertEquals(20, tree.root!!.key)
        assertNull(tree.root!!.parent)
        assertEquals(15, tree.root!!.left!!.key)
        assertNull(tree.root!!.left!!.right)
        assertEquals(10, tree.root!!.left!!.left!!.key)
        assertNull(tree.root!!.left!!.left!!.left)
        assertNull(tree.root!!.left!!.left!!.right)
    }

    @Test
    fun searchTreeInTheDown() {
        val tree = SplayTree()
        tree.insert(15)
        tree.insert(20)
        tree.insert(10)

        assertTrue(tree.search(15))

        assertEquals(15, tree.root!!.key)
        assertNull(tree.root!!.parent)
        assertEquals(10, tree.root!!.left!!.key)
        assertNull(tree.root!!.left!!.right)
        assertNull(tree.root!!.left!!.left)
        assertEquals(20, tree.root!!.right!!.key)
        assertNull(tree.root!!.right!!.left)
        assertNull(tree.root!!.right!!.right)
    }

    @Test
    fun deleteTest() {
        val tree = SplayTree()
        tree.insert(15)
        tree.insert(20)
        tree.insert(10)

        tree.remove(10)

        assertEquals(20, tree.root!!.key)
        assertNull(tree.root!!.parent)
        assertNull(tree.root!!.right)
        assertEquals(15, tree.root!!.left!!.key)
        assertNull(tree.root!!.left!!.left)
        assertNull(tree.root!!.left!!.right)
    }
}