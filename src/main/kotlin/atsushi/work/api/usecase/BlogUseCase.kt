package atsushi.work.api.usecase

import atsushi.work.api.model.BlogArticle
import atsushi.work.api.model.BlogArticlePagingList
import atsushi.work.api.model.exception.NotFoundException
import atsushi.work.api.repository.BlogRepository
import org.joda.time.DateTime
import org.springframework.stereotype.Component

@Component
class BlogUseCase(
    private val blogRepository: BlogRepository
) {
    fun getList(page: Int, num: Int): BlogArticlePagingList {
        val offset = num * (page - 1)
        val list = blogRepository.getList(num, offset)
        val articleNum = blogRepository.getArticleNum()
        val pageNum = (articleNum + num - 1) / num
        return BlogArticlePagingList(
            list = list,
            currentPage = page,
            pageNum = pageNum
        )
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
