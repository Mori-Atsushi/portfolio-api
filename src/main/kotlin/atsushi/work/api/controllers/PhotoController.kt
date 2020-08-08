package atsushi.work.api.controllers

import atsushi.work.api.controllers.response.PhotoDataResponse
import atsushi.work.api.controllers.response.PhotoDataListResponse
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
    ): PhotoDataListResponse = photoUseCase.getJsonList(
            page ?: 1,
            num ?: 20
    )

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET])
    fun item(@PathVariable("id") id: Int): PhotoDataResponse =
            photoUseCase.getItem(id) ?: throw NotFoundException()
}
