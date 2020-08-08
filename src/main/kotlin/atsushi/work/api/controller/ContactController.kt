package atsushi.work.api.controller

import atsushi.work.api.controller.mapper.toModel
import atsushi.work.api.controller.request.ContactRequest
import atsushi.work.api.usecase.ContactUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/contact")
class ContactController(
    val contactUseCase: ContactUseCase
) {
    @RequestMapping(method = [RequestMethod.POST])
    fun send(@RequestBody contact: ContactRequest) =
        contactUseCase.send(contact.toModel())
}
