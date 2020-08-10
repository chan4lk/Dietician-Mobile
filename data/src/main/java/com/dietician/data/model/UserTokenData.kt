package com.dietician.data.model

data class UserTokenData(
    val id: Long,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val token: String
)