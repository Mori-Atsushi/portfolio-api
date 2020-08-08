package atsushi.work.api.repositorys

import atsushi.work.api.datasources.db.ArticleDB
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

    /**
     * ブログが読まれたことを保存する
     *
     * @args id ブログID
     */
    fun readItem(id: Int) {
        articleDB.readItem(id)
    }
}
