package atsushi.work.api.datasources.db

import atsushi.work.api.entities.CategoryData
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

object CategoriesTable : Table() {
    val id = CategoriesTable.integer("id").autoIncrement().primaryKey()
    val name = CategoriesTable.text("name")
}

object CategoryTreeTable : Table() {
    val ancestor = (integer("ancestor") references CategoriesTable.id).primaryKey()
    val descendant = (integer("descendant") references CategoriesTable.id).primaryKey()
    val pathLength = CategoryTreeTable.integer("path_length")
}

@Component
class Category(
        config: Config
) {
    init {
        config.setup()
        transaction {
            SchemaUtils.create(CategoriesTable)
            SchemaUtils.create(CategoryTreeTable)
        }
    }

    fun getAncestors(id: Int): List<CategoryData> = transaction {
        CategoriesTable
                .innerJoin(
                        otherTable = CategoryTreeTable,
                        onColumn = { CategoriesTable.id },
                        otherColumn = { CategoryTreeTable.ancestor }
                )
                .select {
                    CategoryTreeTable.descendant eq id
                }
                .orderBy(CategoryTreeTable.pathLength to false)
                .toCategoryList()
    }
}

private fun Query.toCategoryList(): List<CategoryData> = this.map {
    CategoryData(
            it[CategoriesTable.id],
            it[CategoriesTable.name]
    )
}
