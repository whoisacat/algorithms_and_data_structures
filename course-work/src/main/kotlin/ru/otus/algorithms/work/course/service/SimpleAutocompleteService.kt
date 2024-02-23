package ru.otus.algorithms.work.course.service

import org.springframework.stereotype.Service
import ru.otus.algorithms.work.course.Trie
import ru.otus.algorithms.work.course.persistence.ReferenceBookRepository
import ru.otus.algorithms.work.course.referentialBookSeparator

@Service
class SimpleAutocompleteService(private val referenceBookRepository: ReferenceBookRepository): AutocompleteService {
    override fun prepareReferenceBook(userId: Long) {
        TODO("Not yet implemented")
    }

    override fun getReferenceBook(userId: Long): String {
        return referenceBookRepository
            .findByUserId(userId)
            .getNextLetterWordsInDepth(Trie.MAX_DEPTH)
            .joinToString(referentialBookSeparator)
    }

    override fun getCompletedWords(text: String, userId: Long): Array<String> {
        val trie = referenceBookRepository.findByUserId(userId)
        val word = trie.getWord(text) ?: return emptyArray()
        return arrayOf(word)

    }
}
