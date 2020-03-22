package atsushi.work.api.entities

data class CategoryData(
    val id: Int,
    val name: String
)

data class CategoryDataWithParents(
    val parentId: Int?,
    val id: Int,
    val name: String
)
