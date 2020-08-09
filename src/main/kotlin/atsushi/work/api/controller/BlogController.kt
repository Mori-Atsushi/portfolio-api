package atsushi.work.api.controller

import atsushi.work.api.controller.mapper.toResponse
import atsushi.work.api.controller.response.BlogArticleListResponse
import atsushi.work.api.controller.response.BlogArticlePagingListResponse
import atsushi.work.api.controller.response.BlogArticleResponse
import atsushi.work.api.model.exception.NotFoundException
import atsushi.work.api.usecase.BlogUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/blog")
class BlogController(
    private val blogUseCase: BlogUseCase
) {
    @RequestMapping(method = [RequestMethod.GET])
    fun list(
        @RequestParam("page") page: Int?,
        @RequestParam("num") num: Int?
    ): BlogArticlePagingListResponse = blogUseCase.getList(
        page ?: 1,
        num ?: 15
    ).toResponse()

    @RequestMapping(value = ["/popular"], method = [RequestMethod.GET])
    fun popularList(): BlogArticleListResponse =
        blogUseCase.getPopularList().toResponse()

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET])
    fun item(@PathVariable("id") id: Int): BlogArticleResponse =
        blogUseCase.getItem(id)?.toResponse() ?: throw NotFoundException()

    @RequestMapping(value = ["/{id}/read"], method = [RequestMethod.POST])
    fun read(@PathVariable("id") id: Int) =
        blogUseCase.readItem(id)
}
