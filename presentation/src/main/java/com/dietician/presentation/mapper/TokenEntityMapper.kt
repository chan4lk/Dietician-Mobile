package com.dietician.presentation.mapper

import com.dietician.domain.entities.UserTokenEntity
import com.dietician.presentation.model.Token
import javax.inject.Inject

class TokenEntityMapper @Inject constructor() : Mapper<UserTokenEntity, Token> {
    override fun from(e: Token): UserTokenEntity {
        return UserTokenEntity(
            id = e.id,
            email = e.email,
            password = e.password,
            firstName = e.firstName,
            lastName = e.lastName,
            token = e.token
        )
    }

    override fun to(t: UserTokenEntity): Token {
        return Token(
            id = t.id,
            email = t.email,
            password = t.password,
            firstName = t.firstName,
            lastName = t.lastName,
            token = t.token
        )
    }


}