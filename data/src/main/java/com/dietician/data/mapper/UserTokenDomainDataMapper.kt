package com.dietician.data.mapper

import com.dietician.data.model.UserTokenData
import com.dietician.domain.entities.UserTokenEntity
import javax.inject.Inject

class UserTokenDomainDataMapper @Inject constructor() : Mapper<UserTokenEntity, UserTokenData> {
    override fun from(e: UserTokenData): UserTokenEntity {
        return UserTokenEntity(
            id = e.id,
            email = e.email,
            password = e.password,
            firstName = e.firstName,
            lastName = e.lastName,
            token = e.token
        )
    }

    override fun to(t: UserTokenEntity): UserTokenData {
        return UserTokenData(
            id = t.id,
            email = t.email,
            password = t.password,
            firstName = t.firstName,
            lastName = t.lastName,
            token = t.token
        )
    }

    override fun from(e: UserTokenData, userId: Long): UserTokenEntity {
        return UserTokenEntity(
            id = userId,
            email = e.email,
            password = e.password,
            firstName = e.firstName,
            lastName = e.lastName,
            token = e.token
        )
    }

    override fun to(t: UserTokenEntity, userId: Long): UserTokenData {
        return UserTokenData(
            id = userId,
            email = t.email,
            password = t.password,
            firstName = t.firstName,
            lastName = t.lastName,
            token = t.token
        )
    }

}