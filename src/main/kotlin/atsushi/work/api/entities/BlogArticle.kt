package atsushi.work.api.entities

import org.joda.time.DateTime

data class BlogArticle(
        val id: Int,
        val title: String,
        val description: String,
        val content: String,
        val isPublic: Boolean,
        val ogpImage: String?,
        val releaseAt: DateTime?,
        val createAt: DateTime,
        val updatedAt: DateTime,
        val categoryId: Int? = null
)