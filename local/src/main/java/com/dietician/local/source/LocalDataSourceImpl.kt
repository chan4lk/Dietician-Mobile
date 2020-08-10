package com.dietician.local.source

import com.dietician.data.model.PlanData
import com.dietician.data.model.ProfileData
import com.dietician.data.model.UserTokenData
import com.dietician.data.repository.LocalDataSource
import com.dietician.local.database.PlanDAO
import com.dietician.local.database.ProfileDAO
import com.dietician.local.database.UserDAO
import com.dietician.local.mapper.Mapper
import com.dietician.local.model.PlanLocal
import com.dietician.local.model.ProfileLocal
import com.dietician.local.model.UserLocal
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val planMapper: Mapper<PlanData, PlanLocal>,
    private val userMapper: Mapper<UserTokenData, UserLocal>,
    private val profileMapper: Mapper<ProfileData, ProfileLocal>,
    private val planDAO: PlanDAO,
    private val userDAO: UserDAO,
    private val profileDAO: ProfileDAO
) : LocalDataSource {
    override fun login(userName: String, password: String): Observable<UserTokenData> {
        return userDAO.getUser(userName)
            .map {
                userMapper.from(it)
            }
    }

    override fun saveUser(user: UserTokenData): Completable {
        return userDAO.addUser(userMapper.to(user, userName = user.email))
    }

    override fun getActiveUser(): Observable<UserTokenData> {
        return userDAO.getActiveUser()
            .map {
                userMapper.from(it)
            }
    }

    override fun getPlans(userName: String): Observable<List<PlanData>> {
        return planDAO.getUserPlans(userName)
            .map { plans ->
                plans.map { planMapper.from(it) }
            }
    }

    override fun savePlans(userName: String, plans: List<PlanData>) {
        return planDAO.addPlans(
            plans = plans.map { planMapper.to(it, userName) }
        )
    }

    override fun saveProfile(userName: String, profile: ProfileData): Completable {
        return profileDAO.addProfile(profileMapper.to(profile, userName))
    }
}

