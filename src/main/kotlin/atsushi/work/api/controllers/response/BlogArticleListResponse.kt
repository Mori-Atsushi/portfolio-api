package atsushi.work.api.controllers.response

data class BlogArticleListResponse(
    override val nextToken: String?,
    override val prevToken: String?,
    override val list: List<BlogArticleResponse>
) : PagingDataListResponse<BlogArticleResponse>
