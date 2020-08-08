package atsushi.work.api.usecase

import atsushi.work.api.model.Contact
import atsushi.work.api.repositorys.ContactRepository
import org.springframework.stereotype.Component

@Component
class ContactUseCase(
    val contactRepository: ContactRepository
) {
    fun send(value: Contact) {
        contactRepository.sendSlack(value)
    }
}
