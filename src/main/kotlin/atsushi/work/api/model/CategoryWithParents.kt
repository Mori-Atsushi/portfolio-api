package atsushi.work.api.model

data class CategoryWithParents(
    val parentId: Int?,
    val id: Int,
    val name: String
)
