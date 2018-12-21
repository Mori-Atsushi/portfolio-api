package atsushi.work.api.usecase

import atsushi.work.api.entities.BlogArticleJson
import atsushi.work.api.helper.mapper.toJson
import atsushi.work.api.repositorys.BlogRepository
import atsushi.work.api.repositorys.CategoryRepository
import org.springframework.stereotype.Component

@Component
class BlogUseCase(
        val blogRepository: BlogRepository,
        val categoryRepository: CategoryRepository
) {
    fun getJsonList(page: Int, num: Int): List<BlogArticleJson> {
        val articles = blogRepository.getList(page, num)
        return articles.map {
            it.toJson(it.categoryId?.let { id ->
                categoryRepository.getAncestors(id)
            })
        }
    }

    fun getItem(id: Int): BlogArticleJson? {
        val article = blogRepository.getItem(id)
        val categories = article?.categoryId?.let {
            categoryRepository.getAncestors(it)
        }
        return article?.toJson(categories)
    }
}