package atsushi.work.api.entities

data class CategoryDataWithParents(
    val parentId: Int?,
    val id: Int,
    val name: String
)
