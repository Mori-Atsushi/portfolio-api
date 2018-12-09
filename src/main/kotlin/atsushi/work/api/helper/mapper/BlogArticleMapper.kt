package atsushi.work.api.helper.mapper

import atsushi.work.api.entities.BlogArticle
import atsushi.work.api.entities.BlogArticleJson

fun List<BlogArticle>.toJson(): List<BlogArticleJson> = this.map {
    it.toJson()
}

fun BlogArticle.toJson(): BlogArticleJson = BlogArticleJson(
        this.id,
        this.title,
        this.description,
        this.content,
        this.isPublic,
        this.ogpImage,
        this.releaseAt?.toString(),
        this.createAt.toString(),
        this.updatedAt.toString()
)