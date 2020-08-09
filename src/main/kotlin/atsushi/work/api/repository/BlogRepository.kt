package atsushi.work.api.repository

import atsushi.work.api.datasource.db.ArticleDB
import atsushi.work.api.model.BlogArticle
import org.joda.time.DateTime
import org.springframework.stereotype.Component

@Component
class BlogRepository(
    val articleDB: ArticleDB
) {
    fun getList(
        limit: Int,
        offset: Int,
        categoryIds: List<Int>? = null
    ): List<BlogArticle> = articleDB.getList(limit, offset, categoryIds)

    fun getPopularList(
        limit: Int,
        offset: Int = 0,
        thresholdDay: DateTime
    ): List<BlogArticle> =
        articleDB.getPopularList(limit, offset, thresholdDay)

    fun getItem(id: Int): BlogArticle? = articleDB.getItem(id)

    /**
     * ブログが読まれたことを保存する
     *
     * @args id ブログID
     */
    fun readItem(id: Int) {
        articleDB.readItem(id)
    }
}
