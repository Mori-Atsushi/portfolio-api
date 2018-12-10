package atsushi.work.api.entities

data class BlogArticleJson(
        val id: Int,
        val title: String,
        val description: String,
        val content: String,
        val ogpImage: String?,
        val releaseAt: String,
        val updatedAt: String,
        val categories: List<CategoryData>?
)