package atsushi.work.api.datasource.db.table

import org.jetbrains.exposed.sql.Table

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
