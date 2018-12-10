package atsushi.work.api.helper.mapper

import atsushi.work.api.entities.BlogArticle
import atsushi.work.api.entities.BlogArticleJson
import atsushi.work.api.entities.CategoryData

fun BlogArticle.toJson(
        categories: List<CategoryData>? = null
): BlogArticleJson = BlogArticleJson(
        this.id,
        this.title,
        this.description,
        this.content,
        this.isPublic,
        this.ogpImage,
        this.releaseAt?.toString(),
        this.createAt.toString(),
        this.updatedAt.toString(),
        categories
)