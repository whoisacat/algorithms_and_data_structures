package ru.otus.algorithms.work.course.service

import java.util.Optional

interface AutocompleteService {
    fun prepareReferenceBook(userId: Long)
    fun getReferenceBook(userId: Long): Optional<String>
    fun getCompletedWords(text: String, userId: Long): Optional<Array<String>>
}
