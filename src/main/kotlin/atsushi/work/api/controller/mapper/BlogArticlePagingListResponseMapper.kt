package atsushi.work.api.controller.mapper

import atsushi.work.api.controller.response.BlogArticlePagingListResponse
import atsushi.work.api.model.BlogArticlePagingList

fun BlogArticlePagingList.toResponse(): BlogArticlePagingListResponse {
    return BlogArticlePagingListResponse(
        list = list.map { it.toResponse() },
        currentPage = currentPage,
        pageNum = pageNum
    )
}