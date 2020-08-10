package com.dietician.data.mapper

import com.dietician.data.model.UserData
import com.dietician.domain.entities.UserEntity
import javax.inject.Inject

class UserDomainDataMapper @Inject constructor() : Mapper<UserEntity, UserData> {
    override fun from(e: UserData): UserEntity {
        return UserEntity(
            id = e.id,
            email = e.email,
            password = e.password,
            firstName = e.firstName,
            lastName = e.lastName
        )
    }

    override fun to(t: UserEntity): UserData {
        return UserData(
            id = t.id,
            email = t.email,
            password = t.password,
            firstName = t.firstName,
            lastName = t.lastName
        )
    }

    override fun from(e: UserData, userId: Long): UserEntity {
        return UserEntity(
            id = userId,
            email = e.email,
            password = e.password,
            firstName = e.firstName,
            lastName = e.lastName
        )
    }

    override fun to(t: UserEntity, userId: Long): UserData {
        return UserData(
            id = userId,
            email = t.email,
            password = t.password,
            firstName = t.firstName,
            lastName = t.lastName
        )
    }

}