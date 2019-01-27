package atsushi.work.api.helper.mapper

import atsushi.work.api.entities.PhotoData
import atsushi.work.api.entities.PhotoDataJson

fun PhotoData.toJson() = PhotoDataJson(
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
