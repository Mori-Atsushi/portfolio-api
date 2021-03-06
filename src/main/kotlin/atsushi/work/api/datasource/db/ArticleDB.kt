package atsushi.work.api.datasource.db

import atsushi.work.api.datasource.db.config.Config
import atsushi.work.api.datasource.db.table.ArticlesReadTable
import atsushi.work.api.datasource.db.table.ArticlesTable
import atsushi.work.api.model.BlogArticle
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import org.springframework.stereotype.Component

@Component
class ArticleDB(
    config: Config
) {
    init {
        config.setup()
        transaction {
            SchemaUtils.create(ArticlesTable)
            SchemaUtils.create(ArticlesReadTable)
        }
    }

    fun getList(
        limit: Int,
        offset: Int,
        categoryIds: List<Int>? = null
    ): List<BlogArticle> = transaction {
        ArticlesTable
            .select {
                if (categoryIds == null) {
                    isPublic()
                } else {
                    isPublic() and inCategoryList(categoryIds)
                }
            }
            .orderBy(ArticlesTable.releaseAt to false)
            .limit(limit, offset = offset)
            .toBlogArticleList()
    }

    fun getPopularList(
        limit: Int,
        offset: Int = 0,
        thresholdDay: DateTime
    ): List<BlogArticle> = transaction {
        (ArticlesTable innerJoin ArticlesReadTable)
            .slice(ArticlesTable.columns + listOf(ArticlesReadTable.id.count()))
            .select { isPublic() and (ArticlesReadTable.readAt greater thresholdDay) }
            .orderBy(ArticlesReadTable.id.count(), false)
            .groupBy(ArticlesReadTable.articleId)
            .limit(limit, offset = offset)
            .toBlogArticleList()
    }

    fun getItem(id: Int): BlogArticle? = transaction {
        ArticlesTable
            .select {
                ArticlesTable.id eq id and isPublic()
            }
            .toBlogArticleList()
            .firstOrNull()
    }

    fun getArticleNum(): Int = transaction {
        ArticlesTable
            .select { isPublic() }
            .count()
    }

    fun readItem(id: Int) = transaction {
        ArticlesReadTable.insert {
            it[articleId] = id
            it[readAt] = DateTime.now()
        }
    }

    private fun isPublic(): Op<Boolean> {
        val now = DateTime.now()

        return Op.build {
            ArticlesTable.isPublic eq true and (
                ArticlesTable.releaseAt.isNull() or
                    ArticlesTable.releaseAt.lessEq(now)
                )
        }
    }

    private fun inCategoryList(ids: List<Int>): Op<Boolean> {
        return Op.build {
            ArticlesTable.categoryId.inList(ids)
        }
    }

    private fun Query.toBlogArticleList(): List<BlogArticle> =
        this.map {
            BlogArticle(
                it[ArticlesTable.id],
                it[ArticlesTable.title],
                it[ArticlesTable.description],
                it[ArticlesTable.content],
                it[ArticlesTable.ogpImage],
                it[ArticlesTable.releaseAt],
                it[ArticlesTable.updatedAt],
                it[ArticlesTable.categoryId]
            )
        }
}
