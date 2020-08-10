package com.dietician.data.repository

import com.dietician.data.mapper.Mapper
import com.dietician.data.model.PlanData
import com.dietician.data.model.ProfileData
import com.dietician.data.model.TokenData
import com.dietician.data.model.UserData
import com.dietician.domain.entities.PlanEntity
import com.dietician.domain.entities.ProfileEntity
import com.dietician.domain.entities.TokenEntity
import com.dietician.domain.entities.UserEntity
import com.dietician.domain.repository.DietRepository
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class DietRepositoryImpl @Inject constructor(
    private val tokenDomainDataMapper: Mapper<TokenEntity, TokenData>,
    private val planDomainDataMapper: Mapper<PlanEntity, PlanData>,
    private val userDomainDataMapper: Mapper<UserEntity, UserData>,
    private val profileMapper: Mapper<ProfileEntity, ProfileData>,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : DietRepository {

    override fun login(userName: String, password: String): Observable<TokenEntity> {
        val tokenObservable = localDataSource.login(userName, password)
            .map { tokenDomainDataMapper.from(it) }

        return remoteDataSource.login(userName, password)
            .map {
                localDataSource.saveToken(userName, it).subscribe()
                tokenDomainDataMapper.from(it)
            }
            .concatWith(tokenObservable)
    }

    override fun getPlans(token: String): Observable<List<PlanEntity>> {
        val localPlans = localDataSource.getPlans(token)
            .map { plans ->
                plans.map { planDomainDataMapper.from(it) }
            }

        return remoteDataSource.getPlans(token)
            .map { plans ->
                localDataSource.savePlans(token, plans)
                plans.map { planDomainDataMapper.from(it) }
            }.onErrorResumeNext(Observable.empty())
            .concatWith(localPlans)
    }

    override fun signUp(user: UserEntity): Observable<Long> {
        val userData = userDomainDataMapper.to(user)
        return remoteDataSource.signUp(userData)
            .map {
                localDataSource.saveUser(userData)
                it
            }
            .onErrorResumeNext(Function { Observable.just(0) })
    }

    override fun saveProfile(profile: ProfileEntity): Observable<Long> {

        return localDataSource.getActiveUser().flatMap { userData ->
            callRemote(profile, userData)
        }
    }

    private fun callRemote(profile: ProfileEntity, userData: UserData): Observable<Long> {
        val profileData = profileMapper.to(profile)
        val userName = userData.email
        val userId = userData.id
        profileData.userId = userId

        return remoteDataSource.saveProfile(profileData)
            .map {
                val profileWithId = ProfileData(
                    id = it,
                    name = profile.name,
                    isVegetarian = profile.isVegetarian,
                    isPregnant = profile.isPregnant,
                    gender = profile.gender,
                    weight = profile.weight,
                    height = profile.height,
                    age = profile.age,
                    userId = userId
                )
                localDataSource.saveProfile(userName, profileWithId).subscribe()
                it
            }
    }

}