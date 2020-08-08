package atsushi.work.api.datasource.db

import atsushi.work.api.datasource.db.config.Config
import atsushi.work.api.datasource.db.table.CategoriesTable
import atsushi.work.api.datasource.db.table.CategoryTreeTable
import atsushi.work.api.model.Category
import atsushi.work.api.model.CategoryTree
import atsushi.work.api.model.CategoryWithParents
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class CategoryDB(
    config: Config
) {
    init {
        config.setup()
        transaction {
            SchemaUtils.create(CategoriesTable)
            SchemaUtils.create(CategoryTreeTable)
        }
    }

    fun getList(): List<CategoryTree> = transaction {
        val categoryTreeAlias = CategoryTreeTable
            .select { CategoryTreeTable.pathLength eq 1 }
            .alias("categoryTree")

        CategoriesTable
            .leftJoin(
                otherTable = categoryTreeAlias,
                onColumn = { CategoriesTable.id },
                otherColumn = { categoryTreeAlias[CategoryTreeTable.descendant] }
            )
            .selectAll()
            .map {
                CategoryWithParents(
                    it[categoryTreeAlias[CategoryTreeTable.ancestor]],
                    it[CategoriesTable.id],
                    it[CategoriesTable.name]
                )
            }
            .toTree()
    }

    fun getItem(name: String): Category? = transaction {
        CategoriesTable
            .select { CategoriesTable.name eq name }
            .toCategoryList()
            .firstOrNull()
    }

    fun getDescendant(name: String): List<Category>? = transaction {
        getItem(name)?.let {
            CategoriesTable
                .innerJoin(
                    otherTable = CategoryTreeTable,
                    onColumn = { CategoriesTable.id },
                    otherColumn = { CategoryTreeTable.descendant }
                )
                .select {
                    CategoryTreeTable.ancestor.inList(
                        listOf(it.id)
                    )
                }
                .orderBy(CategoryTreeTable.pathLength to false)
                .toCategoryList()
        }
    }

    fun getAncestors(id: Int): List<Category> = transaction {
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

    private fun Query.toCategoryList(): List<Category> = this.map {
        Category(
            it[CategoriesTable.id],
            it[CategoriesTable.name]
        )
    }

    private fun List<CategoryWithParents>.toTree(): List<CategoryTree> {
        val nodes = this.map {
            CategoryTree(
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
}
