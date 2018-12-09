package atsushi.work.api.datasources.db

import atsushi.work.api.entities.BlogArticle
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

private object ArticlesTable : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val title = text("title")
    val description = text("description")
    val content = text("content")
    val isPublic = bool("is_public")
    val ogpImage = text("ogp_image").nullable()
    val releaseAt = datetime("release_at").nullable()
    val createAt = datetime("createAt")
    val updatedAt = datetime("updated_at")
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

    fun list(): List<BlogArticle> = transaction {
        ArticlesTable.selectAll().toList()
    }
}

private fun Query.toList(): List<BlogArticle> =
        this.map {
            BlogArticle(
                    it[ArticlesTable.id],
                    it[ArticlesTable.title],
                    it[ArticlesTable.description],
                    it[ArticlesTable.content],
                    it[ArticlesTable.isPublic],
                    it[ArticlesTable.ogpImage],
                    it[ArticlesTable.releaseAt],
                    it[ArticlesTable.createAt],
                    it[ArticlesTable.updatedAt]
            )
        }