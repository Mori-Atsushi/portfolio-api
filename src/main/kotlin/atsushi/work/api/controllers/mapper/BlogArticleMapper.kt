package atsushi.work.api.controllers.mapper

import atsushi.work.api.controllers.response.BlogArticleResponse
import atsushi.work.api.model.BlogArticle

fun BlogArticle.toResponse(): BlogArticleResponse {
    return BlogArticleResponse(
        id = id,
        title = title,
        description = description,
        content = content,
        ogpImage = ogpImage,
        releaseAt = releaseAt.toString(),
        updatedAt = updatedAt.toString()
    )
}