package ru.otus.algorithms.work.course.controller

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import ru.otus.algorithms.work.course.service.AutocompleteService
import java.util.*

@RestController
class AutocompletionRestController(private val autocompleteService: AutocompleteService) {

    @PostMapping("upload-general-reference-book", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadGeneralReferenceBook(@RequestPart("file") file: MultipartFile):ResponseEntity<Void> {
        autocompleteService.uploadGeneralReferenceBook(file)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("upload-reference-book", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadReferenceBook(userId: Long, @RequestPart("file") file: MultipartFile):ResponseEntity<Void> {
        autocompleteService.uploadReferenceBook(userId, file)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("reference-book")
    fun getReferenceBook(userId: Long): ResponseEntity<String> {
        return ResponseEntity.of(Optional.of(autocompleteService.getReferenceBook(userId)))
    }

    @GetMapping("/autocomplete")
    fun autocomplete(text: String, userId: Long): ResponseEntity<Array<String>> {
        return ResponseEntity.of(Optional.of(autocompleteService.getCompletedWords(text, userId)))
    }
}
