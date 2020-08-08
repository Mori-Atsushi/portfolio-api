package atsushi.work.api.controllers.response

data class PhotoDataJson(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnailImage: String,
    val mediumImage: String,
    val largeImage: String,
    val cameraName: String?,
    val lensName: String?,
    val iso: String?,
    val shutterSpeed: String?,
    val fNumber: String?,
    val focalLength: String?,
    val createAt: String,
    val updatedAt: String
)
