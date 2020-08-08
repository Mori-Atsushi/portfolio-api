package atsushi.work.api.datasource.db.table

import org.jetbrains.exposed.sql.Table

object ArticlesReadTable : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val articleId = (integer("article_id") references ArticlesTable.id)
    val readAt = datetime("createAt")
}
