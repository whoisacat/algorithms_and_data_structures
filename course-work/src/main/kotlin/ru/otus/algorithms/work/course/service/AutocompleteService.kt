package ru.otus.algorithms.work.course.service

import org.springframework.web.multipart.MultipartFile

interface AutocompleteService {
    fun uploadGeneralReferenceBook(file: MultipartFile)
    fun uploadReferenceBook(userId: Long, file: MultipartFile)
    fun getReferenceBook(userId: Long): String
    fun getCompletedWords(text: String, userId: Long): Array<String>
}
