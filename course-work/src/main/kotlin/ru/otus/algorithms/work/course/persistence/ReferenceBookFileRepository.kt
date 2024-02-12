package ru.otus.algorithms.work.course.persistence

import org.springframework.stereotype.Component
import java.util.*

@Component
class ReferenceBookFileRepository : ReferenceBookRepository {
    override fun findByUserId(userId: Long): Optional<String> {
        TODO("Not yet implemented")
    }
}