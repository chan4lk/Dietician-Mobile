package com.dietician.presentation.model

data class Token(
    val id: Long,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val token: String
)