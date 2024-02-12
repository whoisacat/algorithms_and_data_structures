package ru.otus.algorithms.work.course.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ru.otus.algorithms.work.course.service.AutocompleteService
import java.util.*

@RestController
class AutocompletionRestController(private val autocompleteService: AutocompleteService) {

    @PostMapping("prepare-reference-book")
    fun prepareReferenceBook(userId: Long):ResponseEntity<Void> {
        autocompleteService.prepareReferenceBook(userId)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("reference-book")
    fun getReferenceBook(userId: Long): ResponseEntity<String> {
        return ResponseEntity.of(autocompleteService.getReferenceBook(userId))
    }

    @GetMapping("/autocomplete")
    fun autocomplete(text: String, userId: Long): ResponseEntity<Array<String>> {
        return ResponseEntity.of(autocompleteService.getCompletedWords(text, userId))
    }
}
