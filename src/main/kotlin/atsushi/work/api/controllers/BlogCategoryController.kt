package atsushi.work.api.controllers

import atsushi.work.api.entities.CategoryTreeData
import atsushi.work.api.repositorys.CategoryRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/category")
class BlogCategoryController(
        val repository: CategoryRepository
) {
    @RequestMapping(method = [RequestMethod.GET])
    fun list(): List<CategoryTreeData> = repository.getList()
}