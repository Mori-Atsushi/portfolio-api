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

@Component
class Article(
        config: Config
) {
    init {
        config.setup()
        transaction {
            SchemaUtils.create(ArticlesTable)
        }
    }

    fun getList(limit: Int, offset: Int): List<BlogArticle> = transaction {
        ArticlesTable
                .select(isPublic())
                .orderBy(ArticlesTable.releaseAt to false)
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

    fun getListFromCategory(ids: List<Int>, limit: Int, offset: Int): List<BlogArticle> = transaction {
        ArticlesTable
                .select {
                    ArticlesTable.categoryId.inList(ids)
                }
                .orderBy(ArticlesTable.releaseAt to false)
                .limit(limit, offset = offset)
                .toBlogArticleList()
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