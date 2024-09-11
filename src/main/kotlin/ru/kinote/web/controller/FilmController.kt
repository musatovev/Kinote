package ru.kinote.web.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType.ALL_VALUE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import ru.kinote.web.dto.Film
import ru.kinote.web.dto.Lists
import ru.kinote.web.dto.response.Error
import ru.kinote.web.repository.ListOfFilmsRepository
import ru.kinote.web.repository.ListsRepository

@RestController
class FilmController {

    @Autowired
    lateinit var listsRepository: ListsRepository

    @Autowired
    lateinit var listOfFilmsRepository: ListOfFilmsRepository

    @PostMapping(
        value = ["film/create"],
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE]
    )
    fun addFilm(@RequestBody listOfFilms: Film): Error? {
        checkIDisUUID(listOfFilms.listId)
        if (listsRepository.findByListId(listOfFilms.listId).isNotEmpty())
            throw ResponseStatusException(HttpStatusCode.valueOf(404), "Нет списка с listId = ${listOfFilms.listId}")
        listOfFilmsRepository.save(listOfFilms)
        return null
    }

    @PostMapping(
        value = ["film/all"],
        consumes = [ALL_VALUE],
        produces = [APPLICATION_JSON_VALUE]
    )
    fun getList(@RequestParam listId: String): List<Film> {
        checkIDisUUID(listId)
        return listOfFilmsRepository.findByListId(listId)
    }

    private fun checkIDisUUID(uuid: String) {
        if (!Regex("^\\{?[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}\\}?\$").matches(uuid))
            throw ResponseStatusException(HttpStatusCode.valueOf(400), "Id должен быть в формате UUID")
    }
}