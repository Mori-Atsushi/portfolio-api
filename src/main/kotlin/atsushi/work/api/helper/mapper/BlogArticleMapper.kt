package atsushi.work.api.helper.mapper

import atsushi.work.api.entities.BlogArticle
import atsushi.work.api.controllers.response.BlogArticleResponse
import atsushi.work.api.entities.CategoryData

fun BlogArticle.toJson(
    categories: List<CategoryData>? = null
): BlogArticleResponse = BlogArticleResponse(
        this.id,
        this.title,
        this.description,
        this.content,
        this.ogpImage,
        this.releaseAt.toString(),
        this.updatedAt.toString(),
        categories
)
