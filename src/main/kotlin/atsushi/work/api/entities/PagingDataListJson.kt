package atsushi.work.api.entities

interface PagingDataListJson<T> {
    val nextToken: String?
    val prevToken: String?
    val list: List<T>
}