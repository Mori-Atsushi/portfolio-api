package atsushi.work.api.datasources.slack

import atsushi.work.api.datasources.slack.model.SlackMessageAttachment
import atsushi.work.api.datasources.slack.model.SlackMessageRequest
import atsushi.work.api.datasources.slack.config.SlackProperties
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.module.kotlin.*
import com.github.kittinunf.fuel.httpPost
import org.springframework.stereotype.Component

@Component
class Slack(
    private val properties: SlackProperties
) {
    fun sendAttachments(
        text: String,
        title: String? = null,
        authorName: String? = null
    ) {
        val webhook = properties.webhookurl
        val body = SlackMessageRequest(
            attachments = listOf(
                SlackMessageAttachment(
                    fallback = text,
                    text = text,
                    authorName = authorName,
                    title = title
                )
            )
        )
        val mapper = jacksonObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
        val json = mapper.writeValueAsString(body)

        webhook.httpPost().body(json).response { _, _, _ -> }
    }
}
