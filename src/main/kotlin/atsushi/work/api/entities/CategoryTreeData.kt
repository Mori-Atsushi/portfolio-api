package atsushi.work.api.entities

data class CategoryTreeData (
        val id: Int,
        val name: String,
        val children: MutableList<CategoryTreeData> = mutableListOf()
)