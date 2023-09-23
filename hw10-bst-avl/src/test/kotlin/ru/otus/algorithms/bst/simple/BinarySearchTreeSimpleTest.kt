package ru.otus.algorithms.bst.simple

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.lang.Error
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class BinarySearchTreeSimpleTest {

    private val randomList: MutableList<Int> = ArrayList()
    private val orderedList: MutableList<Int> = ArrayList()
    private var randomN: Long = 0
    private var orderedN: Long = 0
    private val randomTree = BinarySearchTreeSimple()
    private val orderedTree = BinarySearchTreeSimple()

    @Test
    fun createTwoTrees() {
        val firstTree = BinarySearchTreeSimple()
        val secondTree = BinarySearchTreeSimple()
        assertNotNull(firstTree)
        assertNotNull(secondTree)
    }

    @Test
    fun addRandomElements() {

        val start = System.currentTimeMillis()
        val random = Random(13)
        while (System.currentTimeMillis() - start < 30_000) {
            val key = random.nextInt()
            randomTree.insert(key)
            if (randomN++ % 10 == 0L) {
                randomList.add(key)
            }
        }
        println(System.currentTimeMillis() - start)
        println("start check ${LocalDateTime.now()}")
        assertNotNull(randomTree.getRootElement())
        checkTree(randomTree.getRootElement())
        println(System.currentTimeMillis() - start)
    }

    @Test
    fun addOrderedElements() {

        val start = System.currentTimeMillis()
        val random = Random(13)
        var next = 0;
        while (System.currentTimeMillis() - start < 1_000) {
            orderedTree.insert(next)
            if (orderedN++ % 10 == 0L) {
                orderedList.add(next)
            }
            next += random.nextInt(3) + 1
        }
        println(System.currentTimeMillis() - start)
        println("start check ${LocalDateTime.now()}")
        assertNotNull(orderedTree.getRootElement())
        checkTree(orderedTree.getRootElement())
        println(System.currentTimeMillis() - start)
    }

    @Test
    fun addOrderedElementsThrowsStackOverflow() {

        val start = System.currentTimeMillis()

        val thrown: Error = assertThrows(
            StackOverflowError::class.java,
            { exceptionExpected(start) },
            "Expected doThing() to throw, but it didn't"
        )
        assertEquals(StackOverflowError::class.java, thrown::class.java)
        println(System.currentTimeMillis() - start)
    }

    private fun exceptionExpected(start: Long) {
        val random = Random(13)
        var next = 0;
        while (System.currentTimeMillis() - start < 5_000) {
            println(System.currentTimeMillis() - start)
            orderedTree.insert(next)
            next += random.nextInt(3) + 1
        }
        println(System.currentTimeMillis() - start)
        println("start check ${LocalDateTime.now()}")
        assertNotNull(orderedTree.getRootElement())
        checkTree(orderedTree.getRootElement())
    }

    private fun checkTree(node: Node?) {
        if (node == null) return
        assertTrue (node.left == null
                || node.left!!.key < node.key,
            "left element is not less then root: root {${node.key}} left {${node.left?.key}}")

        assertTrue (node.right == null
                || node.right!!.key > node.key,
            "right element is not grater then root: root {${node.key}} right {${node.right?.key}}")
        if (node.left != null) {
            checkTree(node.left)
        }
        if (node.right != null) {
            checkTree(node.right)
        }
    }

    @Test
    fun searchNotExisting() {
        val tree = BinarySearchTreeSimple()
        tree.insert(5)
        tree.insert(3)
        tree.insert(7)
        assertFalse { tree.search(6) }
        assertFalse { tree.search(1) }
        assertFalse { tree.search(0) }
        assertFalse { tree.search(9) }
        assertTrue { tree.search(7) }
        assertTrue { tree.search(3) }
        assertTrue { tree.search(5) }
    }

    @Test
    fun deleteFromRandom() {
        for (i in randomList) {
            assertTrue { randomTree.search(i) }
        }
        for (i in randomList) {
            randomTree.remove(i)
        }
        for (i in randomList) {
            assertFalse { randomTree.search(i) }
        }
    }

    @Test
    fun deleteFromOrdered() {
        for (i in orderedList) {
            assertTrue { orderedTree.search(i) }
        }
        for (i in orderedList) {
            orderedTree.remove(i)
        }
        for (i in orderedList) {
            assertFalse { orderedTree.search(i) }
        }
    }


    @Test
    fun simpleTreeTest() {
        val tree = BinarySearchTreeSimple()
        tree.insert(140)
        tree.insert(70)
        tree.insert(50)
        tree.insert(80)
        tree.insert(30)
        tree.insert(36)
        tree.insert(20)
        tree.insert(53)
        tree.insert(51)
        tree.insert(54)
        tree.insert(77)
        tree.insert(82)
        tree.insert(15)
        assertEquals(70, tree.getRootElement()?.left?.key)
        assertEquals(50, tree.getRootElement()?.left?.left?.key)
        assertEquals(30, tree.getRootElement()?.left?.left?.left?.key)
        assertEquals(20, tree.getRootElement()?.left?.left?.left?.left?.key)
        assertEquals(15, tree.getRootElement()?.left?.left?.left?.left?.left?.key)
        assertEquals(36, tree.getRootElement()?.left?.left?.left?.right?.key)
        assertEquals(54, tree.getRootElement()?.left?.left?.right?.right?.key)
        assertEquals(51, tree.getRootElement()?.left?.left?.right?.left?.key)
        assertEquals(53, tree.getRootElement()?.left?.left?.right?.key)
        assertEquals(80, tree.getRootElement()?.left?.right?.key)
        assertEquals(82, tree.getRootElement()?.left?.right?.right?.key)
        assertEquals(77, tree.getRootElement()?.left?.right?.left?.key)
        assertEquals(140, tree.getRootElement()?.key)
    }
}