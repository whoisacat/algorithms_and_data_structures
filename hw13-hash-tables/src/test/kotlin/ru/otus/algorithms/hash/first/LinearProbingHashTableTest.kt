package ru.otus.algorithms.hash.first

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.lang.reflect.Field
import java.util.UUID
import kotlin.math.pow

class LinearProbingHashTableTest {

    @Test
    fun tableCreatingTest() {
        val table = LinearProbingHashTable<Int, String>()

        assertNotNull(table)
        assertEquals(0, table.size())
    }

    @Test
    fun singleElementInsertCountOneTest() {
        val table = LinearProbingHashTable<String, String>()
        table.put("key", "value")

        assertEquals(1, table.size())
    }

    @Test
    fun twoElementInsertCountTwoTest() {
        val table = LinearProbingHashTable<Int, String>()
        table.put(1, "value1")
        table.put(2, "value2")

        assertEquals(2, table.size())
    }

    @Test
    fun sameKeyInsertTwiceCountOneTest() {
        val table = LinearProbingHashTable<String, String>()
        table.put("key", "value1")
        table.put("key", "value2")

        assertEquals(1, table.size())
    }

    @Test
    fun sameKeyInsertTwiceReturnsFirstValueTest() {
        val table = LinearProbingHashTable<String, String>()
        table.put("key", "value1")
        val result = table.put("key", "value2")

        assertEquals("value1", result)
    }

    @Test
    fun sameHashKeyInsertTwiceCountTwo() {
        val table = LinearProbingHashTable<Int, String>(maxCollisions = 1)
        table.put(11, "value1")
        table.put(111, "value2")

        assertEquals(2, table.size())
    }

    @Test
    fun deleteSingleElementSizeZeroTest() {
        val table = LinearProbingHashTable<String, String>()
        table.put("key", "value1")
        table.delete("key")

        assertEquals(0, table.size())
    }

    @Test
    fun deleteSecondOfTwoSizeOneTest() {
        val table = LinearProbingHashTable<Int, String>()
        table.put(1, "value1")
        table.put(11, "value2")
        table.delete(11)

        assertEquals(1, table.size())
    }

    @Test
    fun deleteFirstOfTwoSizeOneTest() {
        val table = LinearProbingHashTable<Int, String>()
        table.put(1, "value1")
        table.put(11, "value2")
        table.delete(1)

        assertEquals(1, table.size())
    }

    @Test
    fun deleteTwoOfTwoSizeZeroTest() {
        val table = LinearProbingHashTable<Int, String>()
        table.put(1, "value1")
        table.put(11, "value2")
        table.delete(1)
        table.delete(11)

        assertEquals(0, table.size())
    }

    @Test
    fun deleteMiddleSizeTwoTest() {
        val table = LinearProbingHashTable<Int, String>()
        table.put(1, "value1")
        table.put(11, "value2")
        table.put(111, "value3")
        table.delete(11)

        assertEquals(2, table.size())
    }

    @Test
    fun deletedIsNotThereTest() {
        val table = LinearProbingHashTable<Int, String>()
        table.put(1, "value1")
        table.put(11, "value2")
        table.put(111, "value3")
        table.delete(11)

        assertEquals(2, table.size())
        assertNull(table.get(11))
    }

    @Test
    fun deleteMiddleExtremeElementsSteelThereTwoTest() {
        val table = LinearProbingHashTable<Int, String>()
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
        val table = LinearProbingHashTable<Int, String>()
        while (table.size() < 1_966_080) {
            val key = (Math.random() * 1_966_089).toInt()
            table.put(key, "value$key")
        }

        val field: Field = table::class.java.getDeclaredField("items")
        field.isAccessible = true
        assertEquals(10 * (2.0.pow(18.0).toInt()), (field.get(table) as Array<Item<Int, String>>).size)
    }

    @Test
    fun afterInserting2_400_000LoadFactorMoreThan08Test() {
        val table = LinearProbingHashTable<String, String>(maxLoadFactor = 0.9, maxCollisions = 1000)
        val count = 2_300_000
        while (table.size() < count) {
            val key = UUID.randomUUID().toString()
            table.put(key, "value$key")
        }

        assertEquals(count, table.size())
        val field: Field = table::class.java.getDeclaredField("loadFactor")
        field.isAccessible = true
        assertTrue(0.85 < field.get(table) as Double, "actual loadFactor is ${field.get(table)}")
    }

    @Test
    fun afterInserting2_324_000LoadFactorLessThan05Test() {
        val table = LinearProbingHashTable<Int, String>()
        val count = 2_324_000
        while (table.size() < count) {
            val key = (Math.random() * 3_000_000).toInt()
            table.put(key, "value$key")
        }

        assertEquals(count, table.size())
        val field: Field = table::class.java.getDeclaredField("loadFactor")
        field.isAccessible = true
        assertTrue(0.5 > field.get(table) as Double, "actual factor is ${field.get(table)}")
    }

    @Test
    fun addALotTest() {

        val table = LinearProbingHashTable<Int, String>()
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
        val table = LinearProbingHashTable<Int, String>()
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

    @Test
    fun freeMemTest() {
        val table = LinearProbingHashTable<Int, Int>(minLoadFactor = 0.3)
        for (i in 1..15) table.put(i, i)
        for (i in 3..14) table.delete(i)

        val field: Field = table::class.java.getDeclaredField("items")
        field.isAccessible = true
        assertEquals(10, (field.get(table) as Array<Item<Int, String>>).size,
            "actual factor is ${(field.get(table) as Array<Item<Int, String>>).size}")
    }
}