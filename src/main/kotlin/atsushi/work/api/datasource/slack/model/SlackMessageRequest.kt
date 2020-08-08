package atsushi.work.api.datasource.slack.model

data class SlackMessageRequest(
    val attachments: List<SlackMessageAttachment>? = null
)
