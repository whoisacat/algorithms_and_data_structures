package ru.otus.algorithms.work.course.service

interface AutocompleteService {
    fun prepareReferenceBook(userId: Long)
    fun getReferenceBook(userId: Long): String
    fun getCompletedWords(text: String, userId: Long): Array<String>
}
