package com.dietician.domain.entities

data class UserTokenEntity(
    val id: Long,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val token: String
)