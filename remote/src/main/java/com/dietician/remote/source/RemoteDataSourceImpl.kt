package com.dietician.remote.source

import com.dietician.data.model.*
import com.dietician.data.repository.RemoteDataSource
import com.dietician.domain.repository.TokenRepository
import com.dietician.remote.api.*
import com.dietician.remote.mapper.Mapper
import com.dietician.remote.model.*
import io.reactivex.Observable
import io.reactivex.ObservableSource
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val authApi: AuthApi,
    private val planApi: PlanApi,
    private val profileApi: ProfileApi,
    private val dietApi: DietApi,
    private val progressApi: ProgressApi,
    private val planMapper: Mapper<PlanData, Plan>,
    private val userMapper: Mapper<UserData, User>,
    private val profileMapper: Mapper<ProfileData, Profile>,
    private val dietMapper: Mapper<DietData, Diet>,
    private val progressMapper: Mapper<ProgressData, Progress>,
    private val tokenRepository: TokenRepository
) : RemoteDataSource {
    override fun login(userName: String, password: String): Observable<UserTokenData> {
        val credential = Credential(userName, password)
        return authApi.login(credential)
            .switchMap { response -> getUserDetails(userName, password, response) }
    }

    private fun getUserDetails(
        userName: String,
        password: String,
        response: TokenData
    ): ObservableSource<out UserTokenData>? {

        // Set token for next api call.
        tokenRepository.setTokenValue(response.token)

        return authApi.getUserDetails(userName)
            .map { user ->
                UserTokenData(
                    id = user.id,
                    token = response.token,
                    password = password,
                    email = user.email,
                    lastName = user.surname,
                    firstName = user.name
                )
            }
    }

    override fun getPlans(userId: Long): Observable<List<PlanData>> {
        return planApi.getPlans(userId).map { response ->
            response.list.map { plan -> planMapper.from(plan) }
        }
    }

    override fun signUp(user: UserData): Observable<Long> {
        return authApi.signUp(userMapper.to(user))
    }

    override fun saveProfile(profile: ProfileData): Observable<Long> {
        return if (profile.id > 0) {
            profileApi.updateProfile(profile.id, profile = profileMapper.to(profile))
        } else {
            profileApi.saveProfile(profileMapper.to(profile))
        }
    }

    override fun getProfile(userId: Long): Observable<ProfileData> {
        return profileApi.getProfile(userId)
            .map { profile ->
                profileMapper.from(profile)
            }
    }

    override fun savePlan(planData: PlanData): Observable<Long> {
        return planApi.savePlan(planMapper.to(planData))
    }

    override fun getDiet(planId: Long, userId: Long): Observable<DietData> {
        return dietApi.getDiet(userId, planId, Date().formatToServerDateDefaults(), 1)
            .map { diet ->
                dietMapper.from(diet)
            }
    }

    override fun getProgress(userId: Long): Observable<List<ProgressData>> {
        return progressApi.getProgress(userId)
            .map {
                it.list.map { progress ->
                    progressMapper.from(progress)
                }
            }
    }

    private fun Date.formatToServerDateDefaults(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(this)
    }

}