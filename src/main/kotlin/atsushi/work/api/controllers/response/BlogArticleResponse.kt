package atsushi.work.api.controllers.response

data class BlogArticleResponse(
    val id: Int,
    val title: String,
    val description: String,
    val content: String,
    val ogpImage: String?,
    val releaseAt: String,
    val updatedAt: String
)
