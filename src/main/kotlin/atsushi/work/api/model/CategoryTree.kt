package atsushi.work.api.model

data class CategoryTree(
    val id: Int,
    val name: String,
    val children: MutableList<CategoryTree> = mutableListOf()
)
