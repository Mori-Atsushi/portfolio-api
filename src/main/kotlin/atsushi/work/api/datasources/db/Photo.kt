package atsushi.work.api.datasources.db

import atsushi.work.api.entities.BlogArticle
import atsushi.work.api.entities.PhotoData
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

object PhotosTable : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val title = text("title")
    val description = text("description")
    val thumbnailImage = text("thumbnail_image")
    val mediumImage = text("medium_image")
    val largeImage = text("large_image")
    val cameraName = text("camera_name").nullable()
    val lensName = text("lens_name").nullable()
    val iso = text("iso").nullable()
    val shutterSpeed = text("shutter_speed").nullable()
    val fNumber = text("f_number").nullable()
    val focalLength = text("focal_length").nullable()
    val createAt = datetime("create_at")
    val updatedAt = datetime("updated_at")
}

@Component
class Photo(
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