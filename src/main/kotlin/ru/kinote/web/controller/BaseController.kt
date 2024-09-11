package ru.kinote.web.controller

import org.springframework.http.HttpStatusCode
import org.springframework.web.server.ResponseStatusException

open class BaseController {

    fun checkIDisUUID(uuid: String) {
        if (!Regex("^\\{?[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}\\}?\$").matches(uuid))
            throw ResponseStatusException(HttpStatusCode.valueOf(400), "Id должен быть в формате UUID")
    }

}