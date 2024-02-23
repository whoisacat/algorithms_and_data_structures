package ru.otus.algorithms.work.course.persistence

import ru.otus.algorithms.work.course.Trie

interface ReferenceBookRepository {
    fun findByUserId(userId: Long): Trie
}
