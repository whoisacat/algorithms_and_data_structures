package ru.otus.algorithms.work.course.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

import org.mockito.Mockito
import org.mockito.Mockito.`when`
import ru.otus.algorithms.work.course.Trie
import ru.otus.algorithms.work.course.persistence.ReferenceBookRepository

class SimpleAutocompleteServiceTest {

    @Test
    fun getCompletedWords() {

        val userdata = "простите,прощаю,ябидапростите,прощаю,ябидапростите,прощаю,ябидапростите,прощаю,ябидапростите,прощаю,ябида"
        val referenceBookRepository = Mockito.mock(ReferenceBookRepository::class.java)
        val service: AutocompleteService = SimpleAutocompleteService(referenceBookRepository = referenceBookRepository)
        val trie = Trie()
        trie.put(userdata.split(","))
        `when`(referenceBookRepository.findByUserId(13)).thenReturn(trie)

        val suggestion = service.getCompletedWords("яби", 13)

        assertEquals("ябидапростите", suggestion[0])
    }
}