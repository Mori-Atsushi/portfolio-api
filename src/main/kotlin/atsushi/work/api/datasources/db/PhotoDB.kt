package atsushi.work.api.datasources.db

import atsushi.work.api.datasources.db.config.Config
import atsushi.work.api.datasources.db.table.PhotosTable
import atsushi.work.api.entities.PhotoData
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class PhotoDB(
    config: Config
) {
    init {
        config.setup()
        transaction {
            SchemaUtils.create(PhotosTable)
        }
    }

    fun getList(
        limit: Int,
        offset: Int
    ): List<PhotoData> = transaction {
        PhotosTable
            .selectAll()
            .orderBy(PhotosTable.createAt to false)
            .limit(limit, offset = offset)
            .toPhotoDataList()
    }

    fun getItem(id: Int): PhotoData? = transaction {
        PhotosTable
            .select {
                PhotosTable.id eq id
            }
            .toPhotoDataList()
            .firstOrNull()
    }

    private fun Query.toPhotoDataList(): List<PhotoData> =
        this.map {
            PhotoData(
                it[PhotosTable.id],
                it[PhotosTable.title],
                it[PhotosTable.description],
                it[PhotosTable.thumbnailImage],
                it[PhotosTable.mediumImage],
                it[PhotosTable.largeImage],
                it[PhotosTable.cameraName],
                it[PhotosTable.lensName],
                it[PhotosTable.iso],
                it[PhotosTable.shutterSpeed],
                it[PhotosTable.fNumber],
                it[PhotosTable.focalLength],
                it[PhotosTable.createAt],
                it[PhotosTable.updatedAt]
            )
        }
}
