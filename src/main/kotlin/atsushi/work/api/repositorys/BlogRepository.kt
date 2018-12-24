package atsushi.work.api.repositorys

import atsushi.work.api.datasources.db.Article
import atsushi.work.api.entities.BlogArticle
import org.springframework.stereotype.Component

@Component
class BlogRepository(
        val article: Article
) {
    fun getList(
            limit: Int,
            offset: Int,
            categoryIds: List<Int>? = null
    ): List<BlogArticle> = article.getList(limit, offset, categoryIds)

    fun getItem(id: Int): BlogArticle? = article.getItem(id)

    fun isExistPrev(
            limit: Int,
            offset: Int,
            categoryIds: List<Int>? = null
    ): Boolean {
        val prevArticle =
                if (offset > 0) {
                    getList(1, offset - 1, categoryIds)
                } else {
                    emptyList()
                }
        return prevArticle.isNotEmpty()
    }

    fun isExistNext(
            limit: Int,
            offset: Int,
            categoryIds: List<Int>? = null
    ): Boolean {
        val nextArticle = getList(1, offset + limit, categoryIds)
        return nextArticle.isNotEmpty()
    }
}