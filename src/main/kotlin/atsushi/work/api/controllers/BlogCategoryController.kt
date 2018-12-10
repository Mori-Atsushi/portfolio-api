package atsushi.work.api.controllers

import atsushi.work.api.entities.BlogArticleJson
import atsushi.work.api.entities.CategoryTreeData
import atsushi.work.api.helper.exception.NotFoundException
import atsushi.work.api.usecase.CategoryUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/category")
class BlogCategoryController(
        val useCase: CategoryUseCase
) {
    @RequestMapping(method = [RequestMethod.GET])
    fun list(): List<CategoryTreeData> = useCase.getList()

    @RequestMapping(value = ["/{name}"], method = [RequestMethod.GET])
    fun item(@PathVariable("name") name: String): List<BlogArticleJson> =
            useCase.getItem(name)
}