package atsushi.work.api.entities

import org.joda.time.DateTime

data class BlogArticle(
    val id: Int,
    val title: String,
    val description: String,
    val content: String,
    val ogpImage: String?,
    val releaseAt: DateTime,
    val updatedAt: DateTime,
    val categoryId: Int? = null
)
