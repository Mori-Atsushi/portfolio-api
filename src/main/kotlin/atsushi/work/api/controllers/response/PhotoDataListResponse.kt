package atsushi.work.api.controllers.response

data class PhotoDataListResponse(
    override val nextToken: String?,
    override val prevToken: String?,
    override val list: List<PhotoDataResponse>
) : PagingDataListResponse<PhotoDataResponse>
