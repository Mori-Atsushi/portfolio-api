package atsushi.work.api.datasources.db.table

import org.jetbrains.exposed.sql.Table

object CategoryTreeTable : Table() {
    val ancestor = (integer("ancestor") references CategoriesTable.id).primaryKey()
    val descendant = (integer("descendant") references CategoriesTable.id).primaryKey()
    val pathLength = CategoryTreeTable.integer("path_length")
}
