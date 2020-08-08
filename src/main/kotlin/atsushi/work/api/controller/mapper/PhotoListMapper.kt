package atsushi.work.api.controller.mapper

import atsushi.work.api.controller.response.PhotoListResponse
import atsushi.work.api.model.Photo

fun List<Photo>.toResponse(): PhotoListResponse {
    return PhotoListResponse(
        list = map { it.toResponse() }
    )
}
