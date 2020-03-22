package atsushi.work.api.entities

data class ContactRequest(
    val name: String,
    val email: String,
    val message: String
)
