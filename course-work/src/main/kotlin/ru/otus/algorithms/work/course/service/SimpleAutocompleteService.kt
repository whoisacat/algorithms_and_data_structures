package ru.otus.algorithms.work.course.service

import org.springframework.stereotype.Service
import ru.otus.algorithms.work.course.persistence.ReferenceBookRepository
import java.util.*

@Service
class SimpleAutocompleteService(private val referenceBookRepository: ReferenceBookRepository): AutocompleteService {
    override fun prepareReferenceBook(userId: Long) {
        TODO("Not yet implemented")
    }

    override fun getReferenceBook(userId: Long): Optional<String> {
        return referenceBookRepository.findByUserId(userId)
    }

    override fun getCompletedWords(text: String, userId: Long): Optional<Array<String>> {
        TODO("Not yet implemented")
    }
}
