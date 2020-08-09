package com.dietician.remote.model

data class Auth(
    val login: String,
    val password: String,
    val roles: Number
)