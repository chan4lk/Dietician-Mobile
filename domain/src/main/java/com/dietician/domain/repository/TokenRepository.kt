package com.dietician.domain.repository

interface TokenRepository {
    fun getToken(): String

    fun setToken(token: String)
}