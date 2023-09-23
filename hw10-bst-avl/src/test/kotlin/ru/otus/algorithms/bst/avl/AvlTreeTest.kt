package ru.otus.algorithms.bst.avl

import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.test.*

class AvlTreeTest {

    @Test
    fun createTwoTrees() {
        val firstTree = AvlTree()
        val secondTree = AvlTree()
        assertNotNull(firstTree)
        assertNotNull(secondTree)
    }

    @Test
    fun addRandomElements() {

        val randomSet: MutableSet<Int> = HashSet()
        var randomN: Long = 0
        var randomTree = AvlTree()

        val start = System.currentTimeMillis()
        val random = Random(13)
        while (System.currentTimeMillis() - start < 10_000) {
            val key = random.nextInt(1_000_000_000)
            println("inserting $key")
            randomTree.insert(key)
            if (randomN++ % 10 == 0L) {
                randomSet.add(key)
            }
        }
        println(System.currentTimeMillis() - start)
        println("start check ${LocalDateTime.now()}")
        assertNotNull(randomTree.getRootElement())
        checkTree(randomTree.getRootElement())
        println("check is finished ${System.currentTimeMillis() - start}")

        assertFalse(randomSet.isEmpty())
        for (i in randomSet) {
            assertTrue {
                val search = randomTree.search(i)
                println("Search $i $search")
                search
            }
        }
        println("randomList size = ${randomSet.size}")
        for (i in randomSet) {
            println("remove $i")
            randomTree.remove(i)
        }
        for (i in randomSet) {
            assertFalse("i = $i") {
                val search = randomTree.search(i)
                println("Search $i $search")
                search
            }
        }
        randomSet.clear()
        randomTree = AvlTree()
    }

    @Test
    fun addOrderedElements() {

        val orderedList: MutableList<Int> = ArrayList()
        var orderedN: Long = 0
        val orderedTree = AvlTree()

        val start = System.currentTimeMillis()
        val random = Random(13)
        var next = 0;
        while (System.currentTimeMillis() - start < 10_000) {
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

        assertFalse(orderedList.isEmpty())
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

    private fun checkTree(node: AVLNode?) {
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
        val tree = AvlTree()
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
    fun smallRotationWithRootAndNullTest() {
        val tree = AvlTree()
        tree.insert(70)
        tree.insert(40)
        tree.insert(80)
        tree.insert(81)
        tree.insert(50)
        tree.insert(51)

        assertEquals(51, tree.getRootElement()?.left?.right?.key)
        assertEquals(50, tree.getRootElement()?.left?.right?.parent?.key)
        assertEquals(40, tree.getRootElement()?.left?.left?.key)
        assertEquals(50, tree.getRootElement()?.left?.left?.parent?.key)
        assertEquals(50, tree.getRootElement()?.left?.key)
        assertEquals(70, tree.getRootElement()?.left?.parent?.key)
    }

    @Test
    fun smallRotationWithoutRootAndNullTest() {
        val tree = AvlTree()
        tree.insert(40)
        tree.insert(50)
        tree.insert(51)

        assertEquals(50, tree.getRootElement()?.key)
        assertEquals(2, tree.getRootElement()?.height)
        assertEquals(40, tree.getRootElement()?.left?.key)
        assertEquals(1, tree.getRootElement()?.left?.height)
        assertEquals(51, tree.getRootElement()?.right?.key)
        assertEquals(1, tree.getRootElement()?.right?.height)
    }

    @Test
    fun removeFromRootTest() {
        val tree = AvlTree()
        tree.insert(50)
        tree.insert(40)
        tree.insert(51)

        tree.remove(50);
        assertEquals(40, tree.getRootElement()?.key)
    }

    @Test
    fun removeFromTheMiddleTest() {
        val tree = AvlTree()
        tree.insert(50)
        tree.insert(40)
        tree.insert(51)
        tree.insert(30)

        tree.remove(40);
        assertEquals(50, tree.getRootElement()?.key)
        assertEquals(2, tree.getRootElement()?.height)
        assertEquals(1, tree.getRootElement()?.left?.height)
        assertEquals(1, tree.getRootElement()?.right?.height)
        assertEquals(30, tree.getRootElement()?.left?.key)
        assertNull(tree.getRootElement()?.left?.left)
        assertNull(tree.getRootElement()?.left?.right)
        assertNull(tree.getRootElement()?.right?.left)
        assertNull(tree.getRootElement()?.right?.right)
        assertEquals(51, tree.getRootElement()?.right?.key)
    }

    @Test
    fun removeFromTheMiddleWithTwoChildrenTest() {
        val tree = AvlTree()
        tree.insert(50)
        tree.insert(40)
        tree.insert(51)
        tree.insert(30)
        tree.insert(45)

        tree.remove(40);
        assertEquals(50, tree.getRootElement()?.key)
        assertEquals(3, tree.getRootElement()?.height)
        assertEquals(2, tree.getRootElement()?.left?.height)
        assertEquals(1, tree.getRootElement()?.right?.height)
        assertEquals(30, tree.getRootElement()?.left?.key)
        assertEquals(null, tree.getRootElement()?.left?.left?.key)
        assertEquals(45, tree.getRootElement()?.left?.right?.key)
        assertNull(tree.getRootElement()?.left?.left)
        assertNull(tree.getRootElement()?.left?.left)
        assertNull(tree.getRootElement()?.right?.left)
        assertNull(tree.getRootElement()?.right?.right)
        assertEquals(51, tree.getRootElement()?.right?.key)
    }

    @Test
    fun removeFromTheLeftLeaf() {
        val tree = AvlTree()
        tree.insert(50)
        tree.insert(40)
        tree.insert(51)
        tree.insert(30)
        tree.insert(45)

        tree.remove(30);
        assertEquals(50, tree.getRootElement()?.key)
        assertEquals(3, tree.getRootElement()?.height)
        assertEquals(2, tree.getRootElement()?.left?.height)
        assertEquals(1, tree.getRootElement()?.right?.height)
        assertEquals(40, tree.getRootElement()?.left?.key)
        assertEquals(null, tree.getRootElement()?.left?.left?.key)
        assertEquals(45, tree.getRootElement()?.left?.right?.key)
        assertNull(tree.getRootElement()?.left?.left)
        assertNull(tree.getRootElement()?.left?.left)
        assertNull(tree.getRootElement()?.right?.left)
        assertNull(tree.getRootElement()?.right?.right)
        assertEquals(51, tree.getRootElement()?.right?.key)
        assertFalse(tree.search(30))
        assertTrue(tree.search(45))
    }

    @Test
    fun removeFromTheRightLeaf() {
        val tree = AvlTree()
        tree.insert(50)
        tree.insert(40)
        tree.insert(51)
        tree.insert(30)
        tree.insert(45)

        tree.remove(45);
        assertEquals(50, tree.getRootElement()?.key)
        assertEquals(3, tree.getRootElement()?.height)
        assertEquals(2, tree.getRootElement()?.left?.height)
        assertEquals(1, tree.getRootElement()?.right?.height)
        assertEquals(40, tree.getRootElement()?.left?.key)
        assertEquals(null, tree.getRootElement()?.left?.right)
        assertEquals(30, tree.getRootElement()?.left?.left?.key)
        assertNull(tree.getRootElement()?.left?.right)
        assertNull(tree.getRootElement()?.right?.left)
        assertNull(tree.getRootElement()?.right?.right)
        assertEquals(51, tree.getRootElement()?.right?.key)
        assertFalse(tree.search(45))
        assertTrue(tree.search(30))
    }

    @Test
    fun removeFromTheFullRightNode() {
        val tree = AvlTree()
        tree.insert(60)
        tree.insert(29)
        tree.insert(85)
        tree.insert(30)
        tree.insert(27)
        tree.insert(71)
        tree.insert(100)
        tree.insert(31)
        tree.insert(26)
        tree.insert(63)
        tree.insert(80)
        tree.insert(92)
        tree.insert(108)
        tree.insert(73)
        tree.insert(82)
        assertEquals(85, tree.getRootElement()?.right?.key)

        tree.remove(85)

        assertEquals(60, tree.getRootElement()?.key)
        assertEquals(5, tree.getRootElement()?.height)
        assertEquals(82, tree.getRootElement()?.right?.key)
        assertEquals(60, tree.getRootElement()?.right?.parent?.key)
        assertEquals(71, tree.getRootElement()?.right?.left?.key)
        assertEquals(82, tree.getRootElement()?.right?.left?.parent?.key)
        assertEquals(82, tree.getRootElement()?.right?.right?.parent?.key)
        assertEquals(63, tree.getRootElement()?.right?.left?.left?.key)
        assertEquals(71, tree.getRootElement()?.right?.left?.left?.parent?.key)
        assertEquals(80, tree.getRootElement()?.right?.left?.right?.key)
        assertEquals(71, tree.getRootElement()?.right?.left?.right?.parent?.key)
        assertEquals(2, tree.getRootElement()?.right?.left?.right?.height)
        assertEquals(73, tree.getRootElement()?.right?.left?.right?.left?.key)
        assertEquals(80, tree.getRootElement()?.right?.left?.right?.left?.parent?.key)
        assertEquals(1, tree.getRootElement()?.right?.left?.right?.left?.height)
        assertEquals(null, tree.getRootElement()?.right?.left?.right?.right)
    }

    @Test
    fun smallLeftRotationWithRootTest() {
        val tree = AvlTree()
        tree.insert(140)
        assertEquals(1, tree.getRootElement()?.height)
        assertEquals(null, tree.getRootElement()?.left?.height)
        tree.insert(70)
        tree.insert(170)
        tree.insert(190)
        tree.insert(150)
        tree.insert(80)
        tree.insert(145)
        tree.insert(175)
        tree.insert(50)
        tree.insert(77)
        tree.insert(30)
        tree.insert(53)
        tree.insert(51)
        assertEquals(5, tree.getRootElement()?.height)
        assertEquals(4, tree.getRootElement()?.left?.height)
        assertEquals(3, tree.getRootElement()?.left?.left?.height)
        assertEquals(1, tree.getRootElement()?.left?.left?.left?.height)
        assertEquals(2, tree.getRootElement()?.left?.right?.height)
        tree.insert(160)
        tree.insert(200)
        tree.insert(82)
        tree.insert(36)
        tree.insert(20)
        tree.insert(54)
        assertEquals(70, tree.getRootElement()?.left?.key)
        assertEquals(80, tree.getRootElement()?.left?.right?.key)
        assertEquals(50, tree.getRootElement()?.left?.left?.key)
        assertEquals(5, tree.getRootElement()?.height)
        tree.insert(15)
        assertEquals(50, tree.getRootElement()?.left?.key)
        assertEquals(4, tree.getRootElement()?.left?.height)
        assertEquals(70, tree.getRootElement()?.left?.right?.key)
        assertEquals(5, tree.getRootElement()?.height)
    }
}