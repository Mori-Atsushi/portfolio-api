package atsushi.work.api.datasources.db.table

import org.jetbrains.exposed.sql.Table

object CategoriesTable : Table() {
    val id = CategoriesTable.integer("id").autoIncrement().primaryKey()
    val name = CategoriesTable.text("name")
}
