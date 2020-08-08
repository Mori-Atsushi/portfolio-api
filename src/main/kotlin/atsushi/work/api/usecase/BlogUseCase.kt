package atsushi.work.api.usecase

import atsushi.work.api.model.BlogArticle
import atsushi.work.api.helper.exception.NotFoundException
import atsushi.work.api.repositorys.BlogRepository
import org.joda.time.DateTime
import org.springframework.stereotype.Component

@Component
class BlogUseCase(
    private val blogRepository: BlogRepository
) {
    fun getList(page: Int, num: Int): List<BlogArticle> {
        val offset = num * (page - 1)
        return blogRepository.getList(num, offset)
    }

    fun getItem(id: Int): BlogArticle? {
        return blogRepository.getItem(id)
    }

    fun getPopularList(): List<BlogArticle> {
        return blogRepository.getPopularList(
            limit = 20,
            thresholdDay = DateTime.now().minusDays(90)
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
