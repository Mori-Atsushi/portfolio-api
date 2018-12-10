package atsushi.work.api.repositorys

import atsushi.work.api.datasources.db.Article
import atsushi.work.api.entities.BlogArticle
import org.springframework.stereotype.Component

@Component
class BlogRepository(
        val article: Article
) {
    fun getList(): List<BlogArticle> = article.getList()
    fun getItem(id: Int): BlogArticle? = article.getItem(id)
    fun getListFromCategory(id: Int): List<BlogArticle> =
            article.getListFromCategory(id)
}