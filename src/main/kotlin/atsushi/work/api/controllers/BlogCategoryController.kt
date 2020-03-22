package atsushi.work.api.controllers

import atsushi.work.api.entities.BlogArticleListJson
import atsushi.work.api.entities.CategoryTreeData
import atsushi.work.api.usecase.CategoryUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/blog/category")
class BlogCategoryController(
    val useCase: CategoryUseCase
) {
    @RequestMapping(method = [RequestMethod.GET])
    fun list(): List<CategoryTreeData> = useCase.getList()

    @RequestMapping(value = ["/{name}"], method = [RequestMethod.GET])
    fun item(
        @PathVariable("name") name: String,
        @RequestParam("page") page: Int?,
        @RequestParam("num") num: Int?
    ): BlogArticleListJson =
            useCase.getItem(
                    name,
                    page ?: 1,
                    num ?: 20
            )
}
