package ru.kinote.web.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.kinote.web.dto.Lists

interface ListsRepository : MongoRepository<Lists, String> {

    fun findByListId(lastName: String): List<Lists>
}