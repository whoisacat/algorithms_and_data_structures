package ru.otus.algorithms.graph.dijkstra

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import ru.otus.algorithms.text.knuthmorrispratt.*

class MainTest {

    @Test
    fun prefixMatrixTest() {
        val prefixMatrix = prefixMatrix("aabaabaabaaaba")
        assertEquals(15, prefixMatrix.size)
        assertArrayEquals(arrayOf(0, 0, 1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 2, 3, 4), prefixMatrix)
    }

    @Test
    fun knuthMorrisPratPrefixMatrixTest() {
        val prefixMatrix = knuthMorrisPratPrefixMatrix("aabaabaabaaaba")
        assertEquals(15, prefixMatrix.size)
        assertArrayEquals(arrayOf(0, 0, 1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 2, 3, 4), prefixMatrix)
    }

    @Test
    fun emptyTextTest() {
        val text = ""
        val pattern = "abcd"

        assertEquals(Int.MAX_VALUE, low(text, pattern))
    }

    @Test
    fun emptyTextRapidTest() {
        val text = ""
        val pattern = "abcd"

        assertEquals(Int.MAX_VALUE, rapid(text, pattern))
    }

    @Test
    fun emptyPatternTest() {
        val text = "abcd"
        val pattern = ""

        assertEquals(Int.MAX_VALUE, low(text, pattern))
    }

    @Test
    fun emptyPatternRapidTest() {
        val text = "abcd"
        val pattern = ""

        assertEquals(Int.MAX_VALUE, rapid(text, pattern))
    }

    @Test
    fun oneLetterPatternTest() {
        val text = "abcd hgbhjdmfo"
        val pattern = "h"

        assertEquals(5, low(text, pattern))
    }

    @Test
    fun oneLetterPatternRapidTest() {
        val text = "abcd hgbhjdmfo"
        val pattern = "h"

        assertEquals(5, rapid(text, pattern))
    }

    @Test
    fun inTheMiddleTest() {
        val text = "abcabcabcda"
        val pattern = "abcd"

        assertEquals(6, low(text, pattern))
    }

    @Test
    fun inTheMiddleRapidTest() {
        val text = "abcabcabcda"
        val pattern = "abcd"

        assertEquals(6, rapid(text, pattern))
    }

    @Test
    fun inTheStartTest() {
        val text = "abcabcabcda"
        val pattern = "abc"

        assertEquals(0, low(text, pattern))
    }

    @Test
    fun inTheStartRapidTest() {
        val text = "abcabcabcda"
        val pattern = "abc"

        assertEquals(0, rapid(text, pattern))
    }

    @Test
    fun inTheEndTest() {
        val text = "abcabcabcda"
        val pattern = "cda"

        assertEquals(8, low(text, pattern))
    }

    @Test
    fun inTheEndRapidTest() {
        val text = "abcabcabcda"
        val pattern = "cda"

        assertEquals(8, rapid(text, pattern))
    }

    @Test
    fun withoutTest() {
        val text = "abcabcabcda"
        val pattern = "abd"

        assertEquals(Int.MAX_VALUE, low(text, pattern))
    }

    @Test
    fun withoutRapidTest() {
        val text = "abcabcabcda"
        val pattern = "abd"

        assertEquals(Int.MAX_VALUE, rapid(text, pattern))
    }

    @Test
    fun kkolTest() {
        val text = "kfkfkfjdmkkoljfffkj"
        val pattern = "kolokol"

        assertEquals(Int.MAX_VALUE, low(text, pattern))
    }

    @Test
    fun kkolRapidTest() {
        val text = "kfkfkfjdmkkoljfffkj"
        val pattern = "kolokol"

        assertEquals(Int.MAX_VALUE, rapid(text, pattern))
    }
}