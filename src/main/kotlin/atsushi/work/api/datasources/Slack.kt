package atsushi.work.api.datasources

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import org.springframework.stereotype.Component
import com.github.kittinunf.fuel.httpPost
import com.fasterxml.jackson.module.kotlin.*
import org.springframework.boot.context.properties.ConfigurationProperties

@Component
class Slack(
        private val properties: SlackProperties
) {
    fun sendAttachments(
            text: String,
            title: String? = null,
            authorName: String? = null
    ) {
        val webhook = properties.webhookUrl
        val body = SlackMessageRequest(
                attachments = listOf(SlackMessageAttachment(
                        fallback = text,
                        text = text,
                        authorName = authorName,
                        title = title
                ))
        )
        val mapper = jacksonObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
        val json = mapper.writeValueAsString(body)

        webhook.httpPost().body(json).response { _, _, _ -> }
    }
}

@Component
@ConfigurationProperties(prefix = "slack")
class SlackProperties {
    lateinit var webhookUrl: String
}


data class SlackMessageRequest(
        val attachments: List<SlackMessageAttachment>? = null
)

data class SlackMessageAttachment(
        val fallback: String? = null,
        val text: String? = null,
        val authorName: String? = null,
        val title: String? = null
)
