package atsushi.work.api.usecase

import atsushi.work.api.model.ContactRequest
import atsushi.work.api.repositorys.ContactRepository
import org.springframework.stereotype.Component

@Component
class ContactUseCase(
    val contactRepository: ContactRepository
) {
    fun send(value: ContactRequest) {
        contactRepository.sendSlack(value)
    }
}
