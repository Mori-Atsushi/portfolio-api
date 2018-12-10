package atsushi.work.api.repositorys

import atsushi.work.api.datasources.db.Article
import atsushi.work.api.entities.BlogArticle
import org.springframework.stereotype.Component

@Component
class BlogRepository(
        val article: Article
) {
    fun list(): List<BlogArticle> = article.list()
    fun item(id: Int): BlogArticle? = article.item(id)
    fun getListFromCategory(id: Int): List<BlogArticle> =
            article.getListFromCategory(id)
}