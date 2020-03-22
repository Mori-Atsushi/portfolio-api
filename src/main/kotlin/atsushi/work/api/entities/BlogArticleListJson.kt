package atsushi.work.api.entities

data class BlogArticleListJson(
    override val nextToken: String?,
    override val prevToken: String?,
    override val list: List<BlogArticleJson>
) : PagingDataListJson<BlogArticleJson>
