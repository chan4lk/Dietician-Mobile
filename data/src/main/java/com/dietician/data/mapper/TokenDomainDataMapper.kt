package com.dietician.data.mapper

import com.dietician.data.model.TokenData
import com.dietician.domain.entities.TokenEntity
import javax.inject.Inject

class TokenDomainDataMapper @Inject constructor() : Mapper<TokenEntity, TokenData> {
    override fun from(e: TokenData): TokenEntity {
        return TokenEntity(token = e.token)
    }

    override fun to(t: TokenEntity): TokenData {
        return TokenData(token = t.token)
    }
}