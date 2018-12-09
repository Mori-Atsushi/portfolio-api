package atsushi.work.api.entities

data class BlogArticleJson(
        val id: Int,
        val title: String,
        val description: String,
        val content: String,
        val isPublic: Boolean,
        val ogpImage: String?,
        val releaseAt: String?,
        val createAt: String,
        val updatedAt: String
)