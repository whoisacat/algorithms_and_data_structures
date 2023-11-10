package ru.otus.algorithms.bst.random

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RandTreeTest {
    
    @Test
    fun insertOne() {
        val tree = RandTree()

        tree.insert(10)

        assertEquals(10, tree.root!!.key)
    }

    @Test
    fun insertTwo() {
        val tree = RandTree()

        tree.insert(10)
        tree.insert(20)

        assertEquals(2, tree.size)
    }

    @Test
    fun insertTree() {
        val tree = RandTree()

        tree.insert(10)
        tree.insert(20)
        tree.insert(30)

        assertEquals(3, tree.size)
    }

    @Test
    fun insertTreeInTheMiddle() {
        val tree = RandTree()

        tree.insert(10)
        tree.insert(20)
        tree.insert(15)

        assertEquals(3, tree.size)
    }

    @Test
    fun searchTreeInTheMiddle() {
        val tree = RandTree()
        tree.insert(10)
        tree.insert(20)
        tree.insert(15)

        assertTrue(tree.search(20))

        assertEquals(3, tree.size)
    }

    @Test
    fun searchTreeInTheDown() {
        val tree = RandTree()
        tree.insert(15)
        tree.insert(20)
        tree.insert(10)

        assertTrue(tree.search(15))

        assertEquals(3, tree.size)
    }

    @Test
    fun deleteTest() {
        val tree = RandTree()
        tree.insert(15)
        tree.insert(20)
        tree.insert(10)

        tree.remove(10)

        assertEquals(2, tree.size)
    }
}