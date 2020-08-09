package com.dietician.local.mapper

import com.dietician.data.model.UserData
import com.dietician.local.model.UserLocal
import javax.inject.Inject

class UserDataLocalMapper @Inject constructor() : Mapper<UserData, UserLocal> {
    override fun from(e: UserLocal): UserData {
        return UserData(
            id = e.id,
            email = e.userName,
            firstName = e.firstName,
            lastName = e.firstName,
            password = e.password
        )
    }

    override fun to(t: UserData, userName: String): UserLocal {
        return UserLocal(
            id = t.id,
            userName = userName,
            firstName = t.firstName,
            lastName = t.firstName,
            password = t.password
        )
    }

}