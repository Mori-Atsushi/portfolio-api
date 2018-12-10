package atsushi.work.api.datasources.db

import atsushi.work.api.entities.BlogArticle
import atsushi.work.api.entities.CategoryTreeData
import atsushi.work.api.entities.CategoryData
import atsushi.work.api.entities.CategoryDataWithParents
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

    fun getList(): List<CategoryTreeData> = transaction {
        val categoryTreeAlias = CategoryTreeTable
                .select { CategoryTreeTable.pathLength eq 1 }
                .alias("categoryTree")

        CategoriesTable
                .leftJoin(
                        otherTable = categoryTreeAlias,
                        onColumn = { CategoriesTable.id },
                        otherColumn = { categoryTreeAlias[CategoryTreeTable.descendant] })
                .selectAll()
                .map {
                    CategoryDataWithParents(
                            it[categoryTreeAlias[CategoryTreeTable.ancestor]],
                            it[CategoriesTable.id],
                            it[CategoriesTable.name]
                    )
                }
                .toTree()
    }

    fun getItem(name: String): CategoryData? = transaction {
        CategoriesTable
                .select { CategoriesTable.name eq name }
                .toCategoryList()
                .firstOrNull()
    }

    fun getDescendant(name: String): List<CategoryData> = transaction {
        CategoriesTable
                .innerJoin(
                        otherTable = CategoryTreeTable,
                        onColumn = { CategoriesTable.id },
                        otherColumn = { CategoryTreeTable.descendant })
                .select {
                    CategoryTreeTable.ancestor.inList(
                            listOfNotNull(getItem(name)?.id)
                    )
                }
                .orderBy(CategoryTreeTable.pathLength to false)
                .toCategoryList()
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

private fun List<CategoryDataWithParents>.toTree(): List<CategoryTreeData> {
    val nodes = this.map {
        CategoryTreeData(
                it.id,
                it.name
        )
    }

    this.filter { it.parentId != null }
            .forEach { item ->
                nodes.find {
                    it.id == item.parentId
                }?.children?.add(
                        nodes.find { it.id == item.id }!!
                )
            }

    return this.filter { it.parentId == null }
            .mapNotNull { item ->
                nodes.find { it.id == item.id }
            }
}