package atsushi.work.api.datasources.slack.model

data class SlackMessageRequest(
    val attachments: List<SlackMessageAttachment>? = null
)
