package atsushi.work.api.controllers

import atsushi.work.api.controllers.mapper.toResponse
import atsushi.work.api.controllers.response.PhotoListResponse
import atsushi.work.api.controllers.response.PhotoResponse
import atsushi.work.api.helper.exception.NotFoundException
import atsushi.work.api.usecase.PhotoUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/photo")
class PhotoController(
    val photoUseCase: PhotoUseCase
) {
    @RequestMapping(method = [RequestMethod.GET])
    fun list(
        @RequestParam("page") page: Int?,
        @RequestParam("num") num: Int?
    ): PhotoListResponse = photoUseCase.getJsonList(
        page ?: 1,
        num ?: 20
    ).toResponse()

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET])
    fun item(@PathVariable("id") id: Int): PhotoResponse =
        photoUseCase.getItem(id)?.toResponse() ?: throw NotFoundException()
}
