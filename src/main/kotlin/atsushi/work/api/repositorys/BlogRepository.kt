package atsushi.work.api.repositorys

import atsushi.work.api.datasources.db.Article
import atsushi.work.api.entities.BlogArticle
import org.springframework.stereotype.Component

@Component
class BlogRepository(
        val article: Article
) {
    fun getList(limit: Int, offset: Int): List<BlogArticle> = article.getList(limit, offset)

    fun getItem(id: Int): BlogArticle? = article.getItem(id)

    fun getListFromCategory(ids: List<Int>, limit: Int, offset: Int): List<BlogArticle> =
            article.getListFromCategory(ids, limit, offset)
}