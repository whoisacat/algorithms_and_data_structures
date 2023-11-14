package ru.otus.algorithms.graph.dijkstra

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import ru.otus.algorithms.text.boyermoore.fullSearch
import ru.otus.algorithms.text.boyermoore.fullSearch2
import ru.otus.algorithms.text.boyermoore.horspoolSearch
import ru.otus.algorithms.text.boyermoore.patternSuffixSearch

class MainTest {

    @Test
    fun emptyTextTest() {
        val text = ""
        val pattern = "abcd"

        assertEquals(Int.MAX_VALUE, fullSearch(text, pattern))
    }

    @Test
    fun emptyText2Test() {
        val text = ""
        val pattern = "abcd"

        assertEquals(Int.MAX_VALUE, fullSearch2(text, pattern))
    }

    @Test
    fun emptyTextHorspoolTest() {
        val text = ""
        val pattern = "abcd"

        assertEquals(Int.MAX_VALUE, horspoolSearch(text, pattern))
    }

    @Test
    fun emptyTextPatternSuffixSearchTest() {
        val text = ""
        val pattern = "abcd"

        assertEquals(Int.MAX_VALUE, patternSuffixSearch(text, pattern))
    }

    @Test
    fun emptyPatternTest() {
        val text = "abcd"
        val pattern = ""

        assertEquals(Int.MAX_VALUE, fullSearch(text, pattern))
    }

    @Test
    fun emptyPattern2Test() {
        val text = "abcd"
        val pattern = ""

        assertEquals(Int.MAX_VALUE, fullSearch2(text, pattern))
    }

    @Test
    fun emptyPatternHorspoolTest() {
        val text = "abcd"
        val pattern = ""

        assertEquals(Int.MAX_VALUE, horspoolSearch(text, pattern))
    }

    @Test
    fun emptyPatternPatternSuffixSearchTest() {
        val text = "abcd"
        val pattern = ""

        assertEquals(Int.MAX_VALUE, patternSuffixSearch(text, pattern))
    }

    @Test
    fun oneLetterPatternTest() {
        val text = "abcd hgbhjdmfo"
        val pattern = "h"

        assertEquals(5, fullSearch(text, pattern))
    }

    @Test
    fun oneLetterPattern2Test() {
        val text = "abcd hgbhjdmfo"
        val pattern = "h"

        assertEquals(5, fullSearch2(text, pattern))
    }

    @Test
    fun oneLetterPatternHorspoolTest() {
        val text = "abcd hgbhjdmfo"
        val pattern = "h"

        assertEquals(5, horspoolSearch(text, pattern))
    }

    @Test
    fun oneLetterPatternPatternSuffixSearchTest() {
        val text = "abcd hgbhjdmfo"
        val pattern = "h"

        assertEquals(5, patternSuffixSearch(text, pattern))
    }

    @Test
    fun inTheMiddleTest() {
        val text = "abcabcabcda"
        val pattern = "abcd"

        assertEquals(6, fullSearch(text, pattern))
    }

    @Test
    fun inTheMiddle2Test() {
        val text = "abcabcabcda"
        val pattern = "abcd"

        assertEquals(6, fullSearch2(text, pattern))
    }

    @Test
    fun inTheMiddleHorspoolTest() {
        val text = "abcabcabcda"
        val pattern = "abcd"

        assertEquals(6, horspoolSearch(text, pattern))
    }

    @Test
    fun inTheMiddlePatternSuffixSearchTest() {
        val text = "abcabcabcda"
        val pattern = "abcd"

        assertEquals(6, horspoolSearch(text, pattern))
    }

    @Test
    fun inTheStartTest() {
        val text = "abcabcabcda"
        val pattern = "abc"

        assertEquals(0, fullSearch(text, pattern))
    }

    @Test
    fun inTheStart2Test() {
        val text = "abcabcabcda"
        val pattern = "abc"

        assertEquals(0, fullSearch2(text, pattern))
    }

    @Test
    fun inTheStartHorspoolTest() {
        val text = "abcabcabcda"
        val pattern = "abc"

        assertEquals(0, horspoolSearch(text, pattern))
    }

    @Test
    fun inTheStartPatternSuffixSearchTest() {
        val text = "abcabcabcda"
        val pattern = "abc"

        assertEquals(0, patternSuffixSearch(text, pattern))
    }

    @Test
    fun inTheEndTest() {
        val text = "abcabcabcda"
        val pattern = "cda"

        assertEquals(8, fullSearch(text, pattern))
    }

    @Test
    fun inTheEnd2Test() {
        val text = "abcabcabcda"
        val pattern = "cda"

        assertEquals(8, fullSearch2(text, pattern))
    }

    @Test
    fun inTheEndHorspoolTest() {
        val text = "abcabcabcda"
        val pattern = "cda"

        assertEquals(8, horspoolSearch(text, pattern))
    }

    @Test
    fun inTheEndPatternSuffixSearchTest() {
        val text = "abcabcabcda"
        val pattern = "cda"

        assertEquals(8, patternSuffixSearch(text, pattern))
    }

    @Test
    fun withoutTest() {
        val text = "abcabcabcda"
        val pattern = "abd"

        assertEquals(Int.MAX_VALUE, fullSearch(text, pattern))
    }

    @Test
    fun without2Test() {
        val text = "abcabcabcda"
        val pattern = "abd"

        assertEquals(Int.MAX_VALUE, fullSearch2(text, pattern))
    }

    @Test
    fun withoutHorspoolTest() {
        val text = "abcabcabcda"
        val pattern = "abd"

        assertEquals(Int.MAX_VALUE, horspoolSearch(text, pattern))
    }

    @Test
    fun withoutPatternSuffixSearchTest() {
        val text = "abcabcabcda"
        val pattern = "abd"

        assertEquals(Int.MAX_VALUE, patternSuffixSearch(text, pattern))
    }

    @Test
    fun kkolTest() {
        val text = "kfkfkfjdmkkoljfffkj"
        val pattern = "kolokol"

        assertEquals(Int.MAX_VALUE, fullSearch(text, pattern))
    }

    @Test
    fun kkol2Test() {
        val text = "kfkfkfjdmkkoljfffkj"
        val pattern = "kolokol"

        assertEquals(Int.MAX_VALUE, fullSearch2(text, pattern))
    }

    @Test
    fun kkolHorspoolTest() {
        val text = "kfkfkfjdmkkoljfffkj"
        val pattern = "kolokol"

        assertEquals(Int.MAX_VALUE, horspoolSearch(text, pattern))
    }

    @Test
    fun kkolPatternSuffixSearchTest() {
        val text = "kfkfkfjdmkkoljfffkj"
        val pattern = "kolokol"

        assertEquals(Int.MAX_VALUE, patternSuffixSearch(text, pattern))
    }
}