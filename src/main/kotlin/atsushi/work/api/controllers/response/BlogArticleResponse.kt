package atsushi.work.api.controllers.response

import atsushi.work.api.entities.CategoryData

data class BlogArticleResponse(
    val id: Int,
    val title: String,
    val description: String,
    val content: String,
    val ogpImage: String?,
    val releaseAt: String,
    val updatedAt: String,
    val categories: List<CategoryData>?
)
