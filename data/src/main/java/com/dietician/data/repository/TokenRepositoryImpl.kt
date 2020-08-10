package com.dietician.data.repository

import com.dietician.domain.entities.UserTokenEntity
import com.dietician.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor() : TokenRepository {
    private lateinit var token: UserTokenEntity

    override fun getToken(): UserTokenEntity {
        return token
    }

    override fun setToken(token: UserTokenEntity) {
        this.token = token
    }

}