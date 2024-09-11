package ru.kinote.web.dto

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*


@Document("lists")
data class Lists(

    @Id
    val id: String = UUID.randomUUID().toString(),

    val listId: String,

    val name: String?
)
