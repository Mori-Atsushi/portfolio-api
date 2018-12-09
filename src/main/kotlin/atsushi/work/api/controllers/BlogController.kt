package atsushi.work.api.controllers

import atsushi.work.api.entities.BlogArticleJson
import atsushi.work.api.helper.exception.NotFoundException
import org.springframework.web.bind.annotation.*
import atsushi.work.api.usecase.BlogUseCase

@RestController
@RequestMapping("/blog")
class BlogController(
        val blogUseCase: BlogUseCase
) {
    @RequestMapping(method = [RequestMethod.GET])
    fun list(): List<BlogArticleJson> = blogUseCase.getJsonList()

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET])
    fun item(@PathVariable("id") id: Int): BlogArticleJson =
            blogUseCase.getItem(id) ?: throw NotFoundException()
}
