package ru.kinote.web.dto

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID


@Document("film")
class Film(

    @Id
    val id: String = UUID.randomUUID().toString(),

    val listId: String,

    var name: String?,

    var rating: Float?
)