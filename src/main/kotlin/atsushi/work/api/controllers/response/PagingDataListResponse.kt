package atsushi.work.api.controllers.response

interface PagingDataListResponse<T> {
    val nextToken: String?
    val prevToken: String?
    val list: List<T>
}
