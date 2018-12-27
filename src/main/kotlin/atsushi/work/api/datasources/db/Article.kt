package atsushi.work.api.datasources.db

import atsushi.work.api.entities.BlogArticle
import org.joda.time.DateTime
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

object ArticlesTable : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val title = text("title")
    val description = text("description")
    val content = text("content")
    val isPublic = bool("is_public")
    val ogpImage = text("ogp_image").nullable()
    val releaseAt = datetime("release_at")
    val createAt = datetime("createAt")
    val updatedAt = datetime("updated_at")
    val categoryId = (integer("category_id") references CategoriesTable.id).nullable()
}

object ArticlesReadTable : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val articleId = (integer("article_id") references ArticlesTable.id)
    val readAt = datetime("createAt")
}

@Component
class Article(
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

    fun getPopularList(): List<BlogArticle> = transaction {
        (ArticlesTable innerJoin ArticlesReadTable)
                .slice(ArticlesTable.columns + listOf(ArticlesReadTable.id.count()))
                .select { isPublic() }
                .orderBy(ArticlesReadTable.id.count(), false)
                .groupBy(ArticlesReadTable.articleId)
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
