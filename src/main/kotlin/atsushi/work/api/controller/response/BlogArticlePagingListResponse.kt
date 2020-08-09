package atsushi.work.api.controller.response

data class BlogArticlePagingListResponse(
    val list: List<BlogArticleResponse>,
    val currentPage: Int,
    val pageNum: Int
)
