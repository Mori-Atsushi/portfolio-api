package atsushi.work.api.usecase

import atsushi.work.api.model.Photo
import atsushi.work.api.repositorys.PhotoRepository
import org.springframework.stereotype.Component

@Component
class PhotoUseCase(
    val photoRepository: PhotoRepository
) {
    fun getJsonList(page: Int, num: Int): List<Photo> {
        val offset = num * (page - 1)
        return photoRepository.getList(num, offset)
    }

    fun getItem(id: Int): Photo? =
        photoRepository.getItem(id)
}
