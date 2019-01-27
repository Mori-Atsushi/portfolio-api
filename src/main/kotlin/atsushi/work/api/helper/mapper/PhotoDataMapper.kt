package atsushi.work.api.helper.mapper

import atsushi.work.api.entities.PhotoData
import atsushi.work.api.entities.PhotoDataJson

fun PhotoData.toJson() = PhotoDataJson(
        this.id,
        this.title,
        this.description,
        this.createAt.toString(),
        this.updatedAt.toString()
)
