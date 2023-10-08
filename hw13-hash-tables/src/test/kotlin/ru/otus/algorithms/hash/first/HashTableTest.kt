package ru.otus.algorithms.hash.first

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.lang.reflect.Field
import kotlin.math.pow

class HashTableTest {

    @Test
    fun tableCreatingTest() {
        val table = NodesHashTable<Int, String>()

        assertNotNull(table)
        assertEquals(0, table.size())
    }

    @Test
    fun singleElementInsertCountOneTest() {
        val table = NodesHashTable<String, String>()
        table.put("key", "value")

        assertEquals(1, table.size())
    }

    @Test
    fun twoElementInsertCountTwoTest() {
        val table = NodesHashTable<Int, String>()
        table.put(1, "value1")
        table.put(2, "value2")

        assertEquals(2, table.size())
    }

    @Test
    fun sameKeyInsertTwiceCountOneTest() {
        val table = NodesHashTable<String, String>()
        table.put("key", "value1")
        table.put("key", "value2")

        assertEquals(1, table.size())
    }

    @Test
    fun sameKeyInsertTwiceReturnsFirstValueTest() {
        val table = NodesHashTable<String, String>()
        table.put("key", "value1")
        val result = table.put("key", "value2")

        assertEquals("value1", result)
    }

    @Test
    fun deleteSingleElementSizeZeroTest() {
        val table = NodesHashTable<String, String>()
        table.put("key", "value1")
        table.delete("key")

        assertEquals(0, table.size())
    }

    @Test
    fun deleteSecondOfTwoSizeOneTest() {
        val table = NodesHashTable<Int, String>()
        table.put(1, "value1")
        table.put(11, "value2")
        table.delete(11)

        assertEquals(1, table.size())
    }

    @Test
    fun deleteFirstOfTwoSizeOneTest() {
        val table = NodesHashTable<Int, String>()
        table.put(1, "value1")
        table.put(11, "value2")
        table.delete(1)

        assertEquals(1, table.size())
    }

    @Test
    fun deleteTwoOfTwoSizeZeroTest() {
        val table = NodesHashTable<Int, String>()
        table.put(1, "value1")
        table.put(11, "value2")
        table.delete(1)
        table.delete(11)

        assertEquals(0, table.size())
    }

    @Test
    fun deleteMiddleSizeTwoTest() {
        val table = NodesHashTable<Int, String>()
        table.put(1, "value1")
        table.put(11, "value2")
        table.put(111, "value3")
        table.delete(11)

        assertEquals(2, table.size())
    }

    @Test
    fun deleteMiddleExtremeElementsSteelThereTwoTest() {
        val table = NodesHashTable<Int, String>()
        table.put(1, "value1")
        table.put(11, "value2")
        table.put(111, "value3")
        table.delete(11)

        assertEquals("value1", table.get(1))
        assertEquals("value3", table.get(111))
        assertEquals(2, table.size())
    }

    @Test
    fun afterInserting750_000ValuesBucketsSize1_000_000Test() {
        val table = NodesHashTable<Int, String>()
        while (table.size() < 1_966_080) {
            val key = (Math.random() * 1_966_089).toInt()
            table.put(key, "value$key")
        }

        val field: Field = table::class.java.getDeclaredField("buckets")
        field.isAccessible = true
        assertEquals(10 * (2.0.pow(18.0).toInt()), (field.get(table) as Array<Node<Int, String>>).size)
    }

    @Test
    fun afterInserting983_040ValuesSize075Test() {
        val table = NodesHashTable<Int, String>()
        while (table.size() < 1_966_080) {
            val key = (Math.random() * 100_000_000).toInt()
            table.put(key, "value$key")
        }

        assertEquals(1_966_080, table.size())
        val field: Field = table::class.java.getDeclaredField("loadFactor")
        field.isAccessible = true
        assertEquals(0.75, field.get(table))
    }

    @Test
    fun afterInserting983_042ValuesFreeMemTest() {
        val table = NodesHashTable<Int, String>()
        while (table.size() < 983_042) {
            val key = (Math.random() * 983_042).toInt()
            table.put(key, "value$key")
        }

        assertEquals(983_042, table.size())
        val field: Field = table::class.java.getDeclaredField("loadFactor")
        field.isAccessible = true
        assertTrue(0.4 > field.get(table) as Double)
    }

    @Test
    fun addALotTest() {

        val table = NodesHashTable<Int, String>()
        for (i in 1..40) {
            table.put(i, "val$i")
        }
        for (i in 40 downTo 1) {
            assertTrue(table.contains(i), "wasn't $i")
        }
        for (i in 1..40) {
            table.delete(i)
        }
        for (i in 40 downTo 1) {
            assertFalse(table.contains(i), "was $i")
        }
    }

    @Test
    fun allAddedValuesSuccessfullyDeleted() {
        val table = NodesHashTable<Int, String>()
        val set = HashSet<Int>()
        while (table.size() < 983_042) {
            val key = (Math.random() * 10_000_000).toInt()
            set.add(key)
            table.put(key, "value$key")
        }

        assertEquals(set.size, table.size())
        var count = set.size
        for (i in set) {
            table.delete(i)
            assertEquals(--count, table.size())
        }
        assertEquals(0, table.size())
    }
}