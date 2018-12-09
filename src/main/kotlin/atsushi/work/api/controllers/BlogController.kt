package atsushi.work.api.controllers

import atsushi.work.api.entities.BlogArticleJson
import org.springframework.web.bind.annotation.*
import atsushi.work.api.usecase.BlogUseCase

@RestController
@RequestMapping("/blog")
class BlogController(
        val blogUseCase: BlogUseCase
) {
    @RequestMapping(method = [RequestMethod.GET])
    fun list(@RequestParam(value = "name", defaultValue = "World") name: String): List<BlogArticleJson> {
        return blogUseCase.getJsonList()
    }
}
