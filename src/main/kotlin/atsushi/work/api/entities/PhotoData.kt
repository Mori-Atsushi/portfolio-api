package atsushi.work.api.entities

import org.joda.time.DateTime

data class PhotoData (
    val id: Int,
    val title: String,
    val description: String,
    val createAt: DateTime,
    val updatedAt: DateTime
)