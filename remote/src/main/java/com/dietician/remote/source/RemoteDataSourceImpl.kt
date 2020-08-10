package com.dietician.remote.source

import com.dietician.data.model.*
import com.dietician.data.repository.RemoteDataSource
import com.dietician.domain.repository.TokenRepository
import com.dietician.remote.api.AuthApi
import com.dietician.remote.api.PlanApi
import com.dietician.remote.api.ProfileApi
import com.dietician.remote.mapper.Mapper
import com.dietician.remote.mapper.ResponseMapper
import com.dietician.remote.model.Credential
import com.dietician.remote.model.Profile
import com.dietician.remote.model.User
import io.reactivex.Observable
import io.reactivex.ObservableSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val authApi: AuthApi,
    private val planApi: PlanApi,
    private val profileApi: ProfileApi,
    private val mapper: ResponseMapper,
    private val userMapper: Mapper<UserData, User>,
    private val profileMapper: Mapper<ProfileData, Profile>,
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
            response.map { plan -> mapper.mapToPlan(plan) }
        }
    }

    override fun signUp(user: UserData): Observable<Long> {
        return authApi.signUp(userMapper.to(user))
    }

    override fun saveProfile(profile: ProfileData): Observable<Long> {
        return profileApi.saveProfile(profileMapper.to(profile))
    }

    override fun getProfile(userId: Long): Observable<ProfileData> {
        return profileApi.getProfile(userId)
            .map { profile ->
                profileMapper.from(profile)
            }
    }

}