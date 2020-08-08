package atsushi.work.api.controller.mapper

import atsushi.work.api.controller.request.ContactRequest
import atsushi.work.api.model.Contact

fun ContactRequest.toModel(): Contact {
    return Contact(
        name = name,
        email = email,
        message = message
    )
}
