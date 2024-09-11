package ru.kinote.web.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.kinote.web.dto.Film

interface ListOfFilmsRepository : MongoRepository<Film, String> {

    fun findByListId(lastName: String): List<Film>
}