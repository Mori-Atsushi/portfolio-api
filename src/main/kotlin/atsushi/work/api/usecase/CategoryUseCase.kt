package atsushi.work.api.usecase

import atsushi.work.api.entities.BlogArticleJson
import atsushi.work.api.entities.BlogArticleListJson
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

    fun getItem(name: String, page: Int, num: Int): BlogArticleListJson {
        val offset = num * (page - 1)
        val ids = categoryRepository.getDescendant(name)?.map { it.id } ?: listOf()
        val list = blogRepository.getListFromCategory(ids, num, offset)
                .map {
                    it.toJson(it.categoryId?.let { id ->
                        categoryRepository.getAncestors(id)
                    })
                }
        val nextToken = null
        val prevToken = null
        return BlogArticleListJson(
                nextToken,
                prevToken,
                list
        )
    }
}