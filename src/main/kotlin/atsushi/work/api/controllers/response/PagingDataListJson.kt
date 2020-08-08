package atsushi.work.api.controllers.response

interface PagingDataListJson<T> {
    val nextToken: String?
    val prevToken: String?
    val list: List<T>
}
