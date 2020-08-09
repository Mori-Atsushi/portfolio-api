package atsushi.work.api.model

data class BlogArticlePagingList(
    val list: List<BlogArticle>,
    val currentPage: Int,
    val pageNum: Int
)
