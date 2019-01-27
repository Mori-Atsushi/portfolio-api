package atsushi.work.api.datasources.db

import atsushi.work.api.entities.PhotoData
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

object PhotosTable : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val title = text("title")
    val description = text("description")
    val createAt = datetime("createAt")
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
}

private fun Query.toPhotoDataList(): List<PhotoData> =
        this.map {
            PhotoData(
                    it[PhotosTable.id],
                    it[PhotosTable.title],
                    it[PhotosTable.description],
                    it[PhotosTable.createAt],
                    it[PhotosTable.updatedAt]
            )
        }