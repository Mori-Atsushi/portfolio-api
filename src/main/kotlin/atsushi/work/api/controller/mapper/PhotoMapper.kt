package atsushi.work.api.controller.mapper

import atsushi.work.api.controller.response.PhotoResponse
import atsushi.work.api.model.Photo

fun Photo.toResponse(): PhotoResponse {
    return PhotoResponse(
        id = id,
        title = title,
        description = description,
        thumbnailImage = thumbnailImage,
        mediumImage = mediumImage,
        largeImage = largeImage,
        cameraName = cameraName,
        lensName = lensName,
        iso = iso,
        shutterSpeed = shutterSpeed,
        fNumber = fNumber,
        focalLength = focalLength,
        createAt = createAt.toString(),
        updatedAt = updatedAt.toString()
    )
}
