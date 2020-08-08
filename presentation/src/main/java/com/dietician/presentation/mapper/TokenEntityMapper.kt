package com.dietician.presentation.mapper

import com.dietician.domain.entities.TokenEntity
import com.dietician.presentation.model.Token
import javax.inject.Inject

class TokenEntityMapper @Inject constructor() : Mapper<TokenEntity, Token> {
    override fun from(e: Token): TokenEntity {
        return TokenEntity(e.token)
    }

    override fun to(t: TokenEntity): Token {
        return Token(t.token)
    }

}