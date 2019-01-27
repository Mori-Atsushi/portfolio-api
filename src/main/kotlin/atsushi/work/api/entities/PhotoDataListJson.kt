package atsushi.work.api.entities

data class PhotoDataListJson (
        override val nextToken: String?,
        override val prevToken: String?,
        override val list: List<PhotoDataJson>
) : PagingDataListJson<PhotoDataJson>