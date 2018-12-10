package atsushi.work.api.repositorys

import atsushi.work.api.datasources.db.Category
import atsushi.work.api.entities.CategoryData
import org.springframework.stereotype.Component

@Component
class CategoryRepository(
        val category: Category
) {
    fun getList() = category.getList()
    fun getDescendant(name: String): List<CategoryData> = category.getDescendant(name)
    fun getAncestors(id: Int): List<CategoryData> = category.getAncestors(id)
}
