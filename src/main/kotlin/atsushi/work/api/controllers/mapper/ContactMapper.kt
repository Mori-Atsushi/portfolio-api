package atsushi.work.api.controllers.mapper

import atsushi.work.api.controllers.request.ContactRequest
import atsushi.work.api.model.Contact

fun ContactRequest.toModel(): Contact {
    return Contact(
        name = name,
        email = email,
        message = message
    )
}
