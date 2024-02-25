package ru.otus.algorithms.work.course

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.Arrays.asList
import java.util.Collections.singletonList

class TrieTest {

    @Test
    fun createTest() {
        val trie = Trie()

        assertNotNull(trie)
        assertEquals(0, trie.size())
    }

    @Test
    fun insertTest() {
        val trie = Trie()
        val key = "word"

        trie.put(singletonList(key))

        assertEquals(1, trie.size())
        assertEquals(1, trie.getFrequency(key))
        assertEquals("word", trie.getWord(key))
    }

    @Test
    fun insertTwoSameWordsTest() {
        val trie = Trie()
        val key = "word"

        trie.put(asList(key, key))

        assertEquals(1, trie.size())
        assertEquals(2, trie.getFrequency(key))
    }

    @Test
    fun insertTwoDifferentWordsTest() {
        val trie = Trie()
        val key1 = "word1"
        val key2 = "word2"

        trie.put(asList(key1, key2))

        assertEquals(2, trie.size())
        assertEquals(1, trie.getFrequency(key1))
        assertEquals(1, trie.getFrequency(key2))
    }

    @Test
    fun searchPositiveTest() {
        val trie = Trie()
        val key1 = "word1"
        val key2 = "word2"
        val key3 = "word3"
        val key4 = "word4"

        trie.put(asList(key1, key2, key2, key2, key3, key4))

        assertEquals(true, trie.search(key3))
    }

    @Test
    fun searchNegativeTest() {
        val trie = Trie()
        val key1 = "word1"
        val key2 = "word2"
        val key3 = "word3"
        val key4 = "word4"

        trie.put(asList(key1, key2, key2, key2, key4))

        assertEquals(false, trie.search(key3))
    }

    @Test
    fun get() {
        val trie = Trie()
        val key1 = "word1"
        val key2 = "word2"
        val key3 = "word3"
        val key4 = "word4"

        trie.put(asList(key1, key2, key2, key2, key4))

        assertEquals(3, trie.getFrequency(key2))
    }

    @Test
    fun startsWith() {
        val trie = Trie()
        val key1 = "word1"
        val key2 = "word2"
        val key3 = "word3"
        val key4 = "word4"

        trie.put(asList(key1, key2, key2, key2, key4))

        assertEquals(true, trie.startsWith("wor"))
        assertEquals(3, trie.size())
        assertEquals(false, trie.startsWith("word3"))
    }

    @Test
    fun size() {
        val trie = Trie()
        val key1 = "word1"
        val key2 = "word2"
        val key4 = "word4"

        trie.put(asList(key1, key2, key2, key2, key4))

        assertEquals(3, trie.size())
    }

    @Test
    fun contains() {
        val trie = Trie()
        val key1 = "word1"
        val key2 = "word2"
        val key3 = "word3"
        val key4 = "word4"

        trie.put(asList(key1, key2, key2, key2, key4))

        assertEquals(false, trie.contains("wor"))
        assertEquals(false, trie.startsWith(key3))
        assertEquals(true, trie.startsWith(key2))
    }

    @Test
    fun put() {
        val trie = Trie()
        val key1 = "word1"
        val key2 = "word2"

        trie.put(asList(key1, key1, key2))

        assertEquals(2, trie.size())
        assertEquals(2, trie.getFrequency(key1))
        assertEquals(1, trie.getFrequency(key2))
    }

    @Test
    fun oneLetterWordsTest() {
        val trie = Trie()
        val key1 = "word1"
        val key2 = "word2"
        val key3 = "word3"
        val key4 = "word4"
        val key5 = "key5"
        val key6 = "key6"
        val key7 = "key7"

        trie.put(asList(key1, key2, key2, key2, key4, key5, key5, key6, key7))

        val oneLetterWords = trie.getNextLetterWords()
        assertEquals(2, oneLetterWords.size)
        assertArrayEquals(arrayOf(key5, key2), oneLetterWords)
    }

    @Test
    fun treeLetterWordsTest() {
        val trie = Trie()
        val key1 = "word1"
        val key2 = "word2"
        val key3 = "word3"
        val key4 = "word4"
        val key5 = "key5"
        val key6 = "key6"
        val key7 = "key7"

        trie.put(asList(key1, key2, key2, key2, key4, key5, key6, key7))

        val words1 = trie.getNextLetterWords("wor")
        assertEquals(1, words1.size)
        assertArrayEquals(arrayOf(key2), words1)

        val words2 = trie.getNextLetterWords("key")
        assertEquals(3, words2.size)
        assertArrayEquals(arrayOf(key5, key6, key7), words2)

        val words3 = trie.getNextLetterWords("ke")
        assertEquals(1, words3.size)
        assertArrayEquals(arrayOf(key7), words3)
    }

    @Test
    fun getNextLetterWordsInDepth2Test() {
        val trie = Trie()
        val key1 = "word1"
        val key2 = "word2"
        val key3 = "word3"
        val key4 = "word4"
        val key5 = "key5"
        val key6 = "key6"
        val key7 = "key7"

        trie.put(asList(key1, key2, key2, key2, key4, key5, key6, key7))

        val array = trie.getNextLetterWordsInDepth(2)
        array.forEach { print(it) }
        println()
        assertFalse(array.isEmpty())
        assertEquals(2, array.size)
        assertArrayEquals(arrayOf(key7, key2), array)
    }

    @Test
    fun getNextLetterWordsInDepth3Test() {
        val trie = Trie()
        val key1 = "word1"
        val key2 = "word2"
        val key3 = "word3"
        val key4 = "word4"
        val key5 = "key5"
        val key6 = "key6"
        val key7 = "key7"

        trie.put(asList(key1, key2, key2, key2, key4, key5, key6, key7))

        val array3 = trie.getNextLetterWordsInDepth(3)
        array3.forEach { print(it) }
        println()
        assertFalse(array3.isEmpty())
        assertEquals(4, array3.size)
        assertArrayEquals(arrayOf(key5, key6, key7, key2), array3)
    }

    @Test
    fun getNextLetterWordsInDepth4Test() {
        val trie = Trie()
        val key1 = "word1"
        val key2 = "word2"
        val key3 = "word3"
        val key4 = "word4"
        val key5 = "key5"
        val key6 = "key6"
        val key7 = "key7"

        trie.put(asList(key1, key2, key2, key2, key4, key5, key6, key7))

        val array = trie.getNextLetterWordsInDepth(4)
        array.forEach { print(it) }
        println()
        assertFalse(array.isEmpty())
        assertEquals(6, array.size)
        assertArrayEquals(arrayOf(key5, key6, key7, key1, key2, key4), array)
    }

    @Test
    fun getNextLetterWordsInDepth5Test() {
        val trie = Trie()
        val key1 = "word1"
        val key2 = "word2"
        val key3 = "word3"
        val key4 = "word4"
        val key5 = "key5"
        val key6 = "key6"
        val key7 = "key7"

        trie.put(asList(key1, key2, key2, key2, key4, key5, key6, key7))

        val array = trie.getNextLetterWordsInDepth(5)
        array.forEach { print(it) }
        println()
        assertFalse(array.isEmpty())
        assertEquals(6, array.size)
        assertArrayEquals(arrayOf(key5, key6, key7, key1, key2, key4), array)
    }

    @Test
    fun suggestMoreOftenWord() {
        val userdata = "простите,прощаю,ябидапростите,прощаю,ябидапростите,прощаю,ябидапростите,прощаю,ябидапростите,прощаю,ябида"
        val trie = Trie()
        trie.put(userdata.split(","))
        assertEquals("ябидапростите", trie.getWord("яби"))
    }
}
