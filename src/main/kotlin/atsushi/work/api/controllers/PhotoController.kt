package atsushi.work.api.controllers

import atsushi.work.api.entities.PhotoDataListJson
import atsushi.work.api.usecase.PhotoUseCase
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/photo")
class PhotoController(
        val photoUseCase: PhotoUseCase
) {
    @RequestMapping(method = [RequestMethod.GET])
    fun list(
            @RequestParam("page") page: Int?,
            @RequestParam("num") num: Int?
    ): PhotoDataListJson = photoUseCase.getJsonList(
            page ?: 1,
            num ?: 20
    )
}