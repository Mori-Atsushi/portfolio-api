package atsushi.work.api.repositorys

import atsushi.work.api.datasources.slack.Slack
import atsushi.work.api.model.ContactRequest
import org.springframework.stereotype.Component

@Component
class ContactRepository(
    val slack: Slack
) {
    fun sendSlack(value: ContactRequest) = slack.sendAttachments(
            text = value.message,
            title = "${value.name}<${value.email}>"
    )
}
