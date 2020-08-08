package atsushi.work.api.controllers.mapper

import atsushi.work.api.controllers.response.BlogArticleListResponse
import atsushi.work.api.model.BlogArticle

fun List<BlogArticle>.toResponse(): BlogArticleListResponse {
    return BlogArticleListResponse(
        list = map { it.toResponse() }
    )
}
