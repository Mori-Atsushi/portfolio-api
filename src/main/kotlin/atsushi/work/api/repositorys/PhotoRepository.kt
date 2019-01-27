package atsushi.work.api.repositorys

import atsushi.work.api.datasources.db.Photo
import atsushi.work.api.entities.PhotoData
import org.springframework.stereotype.Component

@Component
class PhotoRepository(
    val photo: Photo
) {
    fun getList(
            limit: Int,
            offset: Int
    ): List<PhotoData> = photo.getList(limit, offset)

    fun isExistPrev(
            limit: Int,
            offset: Int
    ): Boolean {
        val prevArticle =
                if (offset > 0) {
                    getList(1, offset - 1)
                } else {
                    emptyList()
                }
        return prevArticle.isNotEmpty()
    }

    fun isExistNext(
            limit: Int,
            offset: Int
    ): Boolean {
        val nextArticle = getList(1, offset + limit)
        return nextArticle.isNotEmpty()
    }

    fun getItem(id: Int): PhotoData? =
            photo.getItem(id)
}