package atsushi.work.api.usecase

import atsushi.work.api.repositorys.CategoryRepository
import org.springframework.stereotype.Component

@Component
class CategoryUseCase(
        val categoryRepository: CategoryRepository
) {
    fun getList() = categoryRepository.getList()
}