package com.dietician.remote.model

data class User(
    val id: Long,
    val name: String,
    val surname: String,
    val email: String,
    val auth: Auth
)