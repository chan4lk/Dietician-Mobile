package com.dietician.domain.entities

data class UserEntity(
    val id: Long,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String
)