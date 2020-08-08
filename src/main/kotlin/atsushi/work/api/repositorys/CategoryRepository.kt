package atsushi.work.api.repositorys

import atsushi.work.api.datasources.db.CategoryDB
import atsushi.work.api.model.Category
import org.springframework.stereotype.Component

@Component
class CategoryRepository(
    val categoryDB: CategoryDB
) {
    fun getList() = categoryDB.getList()
    fun getDescendant(name: String): List<Category>? = categoryDB.getDescendant(name)
    fun getAncestors(id: Int): List<Category> = categoryDB.getAncestors(id)
}
