package atsushi.work.api.repository

import atsushi.work.api.datasource.slack.Slack
import atsushi.work.api.model.Contact
import org.springframework.stereotype.Component

@Component
class ContactRepository(
    val slack: Slack
) {
    fun sendSlack(value: Contact) = slack.sendAttachments(
        text = value.message,
        title = "${value.name}<${value.email}>"
    )
}
