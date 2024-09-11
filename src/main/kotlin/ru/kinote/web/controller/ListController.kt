package ru.kinote.web.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.ALL_VALUE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.kinote.web.dto.Lists
import ru.kinote.web.repository.ListsRepository

@RestController
class ListController : BaseController() {

    @Autowired
    lateinit var listsRepository: ListsRepository

    @PostMapping(
        value = ["list/create"],
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE]
    )
    fun createList(@RequestBody lists: Lists): Lists {
        checkIDisUUID(lists.id)
        listsRepository.save(lists)
        return lists
    }

    @PostMapping(
        value = ["list/all"],
        consumes = [ALL_VALUE],
        produces = [APPLICATION_JSON_VALUE]
    )
    fun getLists(): List<Lists> {
        return listsRepository.findAll()
    }
}