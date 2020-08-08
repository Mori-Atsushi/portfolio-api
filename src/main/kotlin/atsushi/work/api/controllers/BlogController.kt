package atsushi.work.api.controllers

import atsushi.work.api.controllers.response.BlogArticleResponse
import atsushi.work.api.controllers.response.BlogArticleListResponse
import atsushi.work.api.helper.exception.NotFoundException
import atsushi.work.api.usecase.BlogUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/blog")
class BlogController(
    val blogUseCase: BlogUseCase
) {
    @RequestMapping(method = [RequestMethod.GET])
    fun list(
        @RequestParam("page") page: Int?,
        @RequestParam("num") num: Int?
    ): BlogArticleListResponse = blogUseCase.getList(
            page ?: 1,
            num ?: 20
    )

    @RequestMapping(value = ["/popular"], method = [RequestMethod.GET])
    fun popularList(): BlogArticleListResponse =
            blogUseCase.getPopularList()

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET])
    fun item(@PathVariable("id") id: Int): BlogArticleResponse =
            blogUseCase.getItem(id) ?: throw NotFoundException()

    @RequestMapping(value = ["/{id}/read"], method = [RequestMethod.POST])
    fun read(@PathVariable("id") id: Int) =
            blogUseCase.readItem(id)
}
