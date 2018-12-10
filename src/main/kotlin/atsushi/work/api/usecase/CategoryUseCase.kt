package atsushi.work.api.usecase

import atsushi.work.api.entities.BlogArticleJson
import atsushi.work.api.helper.mapper.toJson
import atsushi.work.api.repositorys.BlogRepository
import atsushi.work.api.repositorys.CategoryRepository
import org.springframework.stereotype.Component

@Component
class CategoryUseCase(
        val categoryRepository: CategoryRepository,
        val blogRepository: BlogRepository
) {
    fun getList() = categoryRepository.getList()

    fun getItem(name: String): List<BlogArticleJson> {
        val categories = categoryRepository.getDescendant(name)
        println(categories)
        return categories
                .map {
                    blogRepository.getListFromCategory(it.id)
                }
                .flatten()
                .map {
                    it.toJson(it.categoryId?.let { id ->
                        categoryRepository.getAncestors(id)
                    })
                }
    }
}