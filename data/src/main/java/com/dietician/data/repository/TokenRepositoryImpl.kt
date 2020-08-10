package com.dietician.data.repository

import com.dietician.domain.entities.UserTokenEntity
import com.dietician.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor() : TokenRepository {
    private var tokenValue = ""
    override fun getTokenValue(): String {
        return tokenValue
    }

    override fun setTokenValue(value: String) {
        tokenValue = value
    }

    private var token: UserTokenEntity = UserTokenEntity(
        0,
        "",
        "",
        "",
        "",
        ""
    )

    override fun getToken(): UserTokenEntity {
        return token
    }

    override fun setToken(token: UserTokenEntity) {
        this.token = token
        this.tokenValue = token.token
    }

}