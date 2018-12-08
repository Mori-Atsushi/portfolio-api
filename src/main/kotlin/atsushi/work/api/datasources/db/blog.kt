package atsushi.work.api.datasources.db

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
    val releaseAt = datetime("release_at").nullable()
    val createAt = datetime("createAt")
    val updatedAt = datetime("updated_at")
}

@Component
class Blog(
        properties: Properties
) {
    init {
        Database.connect(properties.url, properties.driverClassName, properties.username, properties.password)
        transaction {
            SchemaUtils.drop(ArticlesTable)
            SchemaUtils.create(ArticlesTable)
        }
    }
}