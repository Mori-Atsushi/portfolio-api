package atsushi.work.api.usecase

import atsushi.work.api.entities.BlogArticleJson
import atsushi.work.api.entities.BlogArticleListJson
import atsushi.work.api.helper.mapper.toJson
import atsushi.work.api.repositorys.BlogRepository
import atsushi.work.api.repositorys.CategoryRepository
import org.springframework.stereotype.Component

@Component
class BlogUseCase(
        val blogRepository: BlogRepository,
        val categoryRepository: CategoryRepository
) {
    fun getJsonList(page: Int, num: Int): BlogArticleListJson {
        val offset = num * (page - 1)
        val articles = blogRepository.getList(num, offset)
        val prevArticle =
                if (offset > 0) {
                    blogRepository.getList(1, offset - 1)
                } else {
                    emptyList()
                }
        val nextArticle = blogRepository.getList(1, offset + num)
        val prevToken = if (prevArticle.isNotEmpty()) "?page=${page - 1}&num=$num" else null
        val nextToken = if (nextArticle.isNotEmpty()) "?page=${page + 1}&num=$num" else null
        val list = articles.map {
            it.toJson(it.categoryId?.let { id ->
                categoryRepository.getAncestors(id)
            })
        }
        return BlogArticleListJson(
                nextToken,
                prevToken,
                list
        )
    }

    fun getItem(id: Int): BlogArticleJson? {
        val article = blogRepository.getItem(id)
        val categories = article?.categoryId?.let {
            categoryRepository.getAncestors(it)
        }
        return article?.toJson(categories)
    }
}