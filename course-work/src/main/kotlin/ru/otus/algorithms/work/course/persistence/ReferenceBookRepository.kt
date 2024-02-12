package ru.otus.algorithms.work.course.persistence

import java.util.*

interface ReferenceBookRepository {
    fun findByUserId(userId: Long): Optional<String>
}
