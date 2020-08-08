package com.dietician.data.repository

import com.dietician.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor() : TokenRepository {
    private var token = ""
    override fun getToken(): String {
        return token
    }

    override fun setToken(token: String) {
        this.token = token
    }

}