package atsushi.work.api.datasource.db.table

import org.jetbrains.exposed.sql.Table

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
