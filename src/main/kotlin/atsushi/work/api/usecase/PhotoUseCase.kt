package atsushi.work.api.usecase

import atsushi.work.api.controllers.response.PhotoDataResponse
import atsushi.work.api.controllers.response.PhotoDataListResponse
import atsushi.work.api.helper.mapper.toJson
import atsushi.work.api.repositorys.PhotoRepository
import org.springframework.stereotype.Component

@Component
class PhotoUseCase(
    val photoRepository: PhotoRepository
) {
    fun getJsonList(page: Int, num: Int): PhotoDataListResponse {
        val offset = num * (page - 1)
        val photos = photoRepository.getList(num, offset)

        val prevToken = if (photoRepository.isExistPrev(num, offset)) {
            "?page=${page - 1}&num=$num"
        } else null
        val nextToken = if (photoRepository.isExistNext(num, offset)) {
            "?page=${page + 1}&num=$num"
        } else null

        val list = photos.map {
            it.toJson()
        }

        return PhotoDataListResponse(
                nextToken,
                prevToken,
                list
        )
    }

    fun getItem(id: Int): PhotoDataResponse? =
            photoRepository.getItem(id)?.toJson()
}
