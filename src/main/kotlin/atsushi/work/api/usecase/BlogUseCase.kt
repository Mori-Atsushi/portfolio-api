package atsushi.work.api.usecase

import atsushi.work.api.controllers.response.BlogArticleResponse
import atsushi.work.api.controllers.response.BlogArticleListResponnse
import atsushi.work.api.helper.exception.NotFoundException
import atsushi.work.api.helper.mapper.toJson
import atsushi.work.api.repositorys.BlogRepository
import atsushi.work.api.repositorys.CategoryRepository
import org.joda.time.DateTime
import org.springframework.stereotype.Component

@Component
class BlogUseCase(
    val blogRepository: BlogRepository,
    val categoryRepository: CategoryRepository
) {
    fun getJsonList(page: Int, num: Int): BlogArticleListResponnse {
        val offset = num * (page - 1)
        val articles = blogRepository.getList(num, offset)

        val prevToken = if (blogRepository.isExistPrev(num, offset)) {
            "?page=${page - 1}&num=$num"
        } else null
        val nextToken = if (blogRepository.isExistNext(num, offset)) {
            "?page=${page + 1}&num=$num"
        } else null

        val list = articles.map {
            it.toJson(it.categoryId?.let { id ->
                categoryRepository.getAncestors(id)
            })
        }

        return BlogArticleListResponnse(
                nextToken,
                prevToken,
                list
        )
    }

    fun getItem(id: Int): BlogArticleResponse? {
        val article = blogRepository.getItem(id)
        val categories = article?.categoryId?.let {
            categoryRepository.getAncestors(it)
        }
        return article?.toJson(categories)
    }

    fun getPopularList(): BlogArticleListResponnse {
        val articles = blogRepository.getPopularList(
            limit = 20,
            thresholdDay = DateTime.now().minusDays(90)
        )
        val list = articles.map {
            it.toJson(it.categoryId?.let { id ->
                categoryRepository.getAncestors(id)
            })
        }
        return BlogArticleListResponnse(
                null,
                null,
                list
        )
    }

    /**
     * ブログが読まれたことを保存する
     *
     * @args id ブログID
     */
    fun readItem(id: Int) {
        val article = blogRepository.getItem(id) ?: throw NotFoundException()
        blogRepository.readItem(article.id)
    }
}
