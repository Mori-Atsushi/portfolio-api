package atsushi.work.api.datasources.slack.setting

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "slack")
class SlackProperties {
    lateinit var webhookurl: String
}
