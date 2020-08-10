package com.dietician.local.mapper

import com.dietician.data.model.UserTokenData
import com.dietician.local.model.UserLocal
import javax.inject.Inject

class UserDataLocalMapper @Inject constructor() : Mapper<UserTokenData, UserLocal> {
    override fun from(e: UserLocal): UserTokenData {
        return UserTokenData(
            id = e.id,
            email = e.userName,
            firstName = e.firstName,
            lastName = e.firstName,
            password = e.password,
            token = e.token
        )
    }

    override fun to(t: UserTokenData, userId: Long): UserLocal {
        return UserLocal(
            id = t.id,
            userName = t.email,
            firstName = t.firstName,
            lastName = t.firstName,
            password = t.password,
            token = t.token
        )
    }

}