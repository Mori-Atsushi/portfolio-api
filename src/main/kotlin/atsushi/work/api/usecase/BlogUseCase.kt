package atsushi.work.api.usecase

import atsushi.work.api.entities.BlogArticleJson
import atsushi.work.api.helper.mapper.toJson
import atsushi.work.api.repositorys.BlogRepository
import org.springframework.stereotype.Component

@Component
class BlogUseCase(
        val blogRepository: BlogRepository
) {
    fun getJsonList(): List<BlogArticleJson> = blogRepository.list().toJson()
}