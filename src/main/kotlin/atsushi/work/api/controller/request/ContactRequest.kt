package atsushi.work.api.controller.request

data class ContactRequest(
    val name: String,
    val email: String,
    val message: String
)
