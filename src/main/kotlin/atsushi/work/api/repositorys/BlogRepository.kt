package atsushi.work.api.repositorys

import atsushi.work.api.datasources.db.Article
import atsushi.work.api.entities.BlogArticle
import org.springframework.stereotype.Component

@Component
class BlogRepository(
        val article: Article
) {
    fun getList(page: Int, num: Int): List<BlogArticle> =
            article.getList(num, num * (page - 1))

    fun getItem(id: Int): BlogArticle? = article.getItem(id)
    fun getListFromCategory(id: Int): List<BlogArticle> =
            article.getListFromCategory(id)
}