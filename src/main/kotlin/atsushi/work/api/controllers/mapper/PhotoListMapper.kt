package atsushi.work.api.controllers.mapper

import atsushi.work.api.controllers.response.PhotoListResponse
import atsushi.work.api.model.Photo

fun List<Photo>.toResponse(): PhotoListResponse {
    return PhotoListResponse(
        list = map { it.toResponse() }
    )
}
