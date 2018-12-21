package atsushi.work.api.entities

data class BlogArticleListJson(
        val nextToken: String?,
        val prevToken: String?,
        val list: List<BlogArticleJson>
)