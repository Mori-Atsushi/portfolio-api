package atsushi.work.api.controllers.request

data class ContactRequest(
    val name: String,
    val email: String,
    val message: String
)
