package com.dietician.remote.mapper

import com.dietician.data.model.UserData
import com.dietician.remote.model.Auth
import com.dietician.remote.model.User
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<UserData, User> {
    override fun from(e: User): UserData {
        return UserData(
            id = e.id,
            email = e.email,
            password = e.auth.password,
            firstName = e.name,
            lastName = e.surname
        )
    }

    override fun to(t: UserData): User {
        return User(
            id = t.id,
            name = t.firstName,
            surname = t.lastName,
            email = t.email,
            auth = Auth(
                login = t.email,
                password = t.password,
                roles = 1
            )
        )
    }

}