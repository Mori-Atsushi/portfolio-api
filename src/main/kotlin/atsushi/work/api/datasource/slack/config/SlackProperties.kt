package atsushi.work.api.datasource.slack.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "slack")
class SlackProperties {
    lateinit var webhookurl: String
}
