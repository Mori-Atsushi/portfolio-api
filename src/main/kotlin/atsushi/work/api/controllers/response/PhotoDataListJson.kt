package atsushi.work.api.controllers.response

data class PhotoDataListJson(
    override val nextToken: String?,
    override val prevToken: String?,
    override val list: List<PhotoDataJson>
) : PagingDataListJson<PhotoDataJson>
