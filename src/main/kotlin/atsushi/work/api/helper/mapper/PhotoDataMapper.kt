package atsushi.work.api.helper.mapper

import atsushi.work.api.model.Photo
import atsushi.work.api.controllers.response.PhotoDataResponse

fun Photo.toJson() = PhotoDataResponse(
        this.id,
        this.title,
        this.description,
        this.thumbnailImage,
        this.mediumImage,
        this.largeImage,
        this.cameraName,
        this.lensName,
        this.iso,
        this.shutterSpeed,
        this.fNumber,
        this.focalLength,
        this.createAt.toString(),
        this.updatedAt.toString()
)
