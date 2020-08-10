package com.dietician.domain.repository

import com.dietician.domain.entities.UserTokenEntity

interface TokenRepository {
    fun getToken(): UserTokenEntity
    fun setToken(token: UserTokenEntity)

    fun getTokenValue(): String
    fun setTokenValue(value: String)
}