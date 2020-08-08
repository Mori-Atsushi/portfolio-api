package atsushi.work.api.controller.mapper

import atsushi.work.api.controller.response.BlogArticleListResponse
import atsushi.work.api.model.BlogArticle

fun List<BlogArticle>.toResponse(): BlogArticleListResponse {
    return BlogArticleListResponse(
        list = map { it.toResponse() }
    )
}
