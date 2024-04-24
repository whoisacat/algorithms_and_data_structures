package ru.otus.algorithms.work.course.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import ru.otus.algorithms.work.course.domain.Trie
import ru.otus.algorithms.work.course.persistence.ReferenceBookRepository
import ru.otus.algorithms.work.course.referentialBookSeparator
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.nio.charset.StandardCharsets

@Service
class SimpleAutocompleteService(private val referenceBookRepository: ReferenceBookRepository): AutocompleteService {
    override fun uploadGeneralReferenceBook(file: MultipartFile) {
        val array = getContent(file.inputStream).split(referentialBookSeparator)
        referenceBookRepository.systemSave(array)
    }

    override fun uploadReferenceBook(userId: Long, file: MultipartFile) {
        val array = getContent(file.inputStream).split(referentialBookSeparator)
        referenceBookRepository.save(userId, array)
    }

    private fun getContent(inputStream: InputStream): String {
        val bufferSize = 1024
        val buffer = CharArray(bufferSize)
        val out = StringBuilder()
        val reader: Reader = InputStreamReader(inputStream, StandardCharsets.UTF_8)
        var numRead: Int
        while (reader.read(buffer, 0, buffer.size).also { numRead = it } > 0) {
            out.appendRange(buffer, 0, numRead)
        }
        return out.toString()
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
