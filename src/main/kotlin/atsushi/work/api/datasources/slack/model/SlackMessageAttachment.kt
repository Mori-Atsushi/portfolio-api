package atsushi.work.api.datasources.slack.model

data class SlackMessageAttachment(
    val fallback: String? = null,
    val text: String? = null,
    val authorName: String? = null,
    val title: String? = null
)
