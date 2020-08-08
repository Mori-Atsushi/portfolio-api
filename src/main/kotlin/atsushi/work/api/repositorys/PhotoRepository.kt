package atsushi.work.api.repositorys

import atsushi.work.api.datasources.db.PhotoDB
import atsushi.work.api.model.Photo
import org.springframework.stereotype.Component

@Component
class PhotoRepository(
    val photoDB: PhotoDB
) {
    fun getList(
        limit: Int,
        offset: Int
    ): List<Photo> = photoDB.getList(limit, offset)

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

    fun getItem(id: Int): Photo? =
            photoDB.getItem(id)
}
