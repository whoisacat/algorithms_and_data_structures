package ru.otus.algorithms.work.course

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class FactorArrayTest {

    @Test
    fun addAll() {
        val factorArray = FactorArray(2)

        assertTrue(factorArray.getContent().isEmpty())

        factorArray.addAll(arrayOf("a", "b"))

        assertEquals(2, factorArray.getContent().size)
        assertEquals(2, factorArray.size())
    }
}
