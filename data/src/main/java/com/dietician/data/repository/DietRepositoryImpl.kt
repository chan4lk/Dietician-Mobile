package com.dietician.data.repository

import com.dietician.data.mapper.Mapper
import com.dietician.data.model.*
import com.dietician.domain.entities.*
import com.dietician.domain.repository.DietRepository
import com.dietician.domain.usecases.GetDietTask
import io.reactivex.Observable
import javax.inject.Inject

class DietRepositoryImpl @Inject constructor(
    private val tokenDomainDataMapper: Mapper<UserTokenEntity, UserTokenData>,
    private val planDomainDataMapper: Mapper<PlanEntity, PlanData>,
    private val userDomainDataMapper: Mapper<UserEntity, UserData>,
    private val profileMapper: Mapper<ProfileEntity, ProfileData>,
    private val dietMapper: Mapper<DietEntity, DietData>,
    private val progressMapper: Mapper<ProgressEntity, ProgressData>,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : DietRepository {

    override fun login(userName: String, password: String): Observable<UserTokenEntity> {
        val tokenObservable = localDataSource.login(userName, password)
            .map { tokenDomainDataMapper.from(it) }

        return remoteDataSource.login(userName, password)
            .map {
                localDataSource.saveUser(it)
                tokenDomainDataMapper.from(it)
            }
            .concatWith(tokenObservable)
    }

    override fun getPlans(userId: Long): Observable<List<PlanEntity>> {
        val localPlans = localDataSource.getPlans(userId)
            .map { plans ->
                plans.map { planDomainDataMapper.from(it) }
            }

        return remoteDataSource.getPlans(userId)
            .map { plans ->
                localDataSource.savePlans(userId, plans)
                plans.map { planDomainDataMapper.from(it) }
            }.onErrorResumeNext(Observable.empty())
            .concatWith(localPlans)
    }

    override fun signUp(user: UserEntity): Observable<UserTokenEntity> {
        val userData = userDomainDataMapper.to(user)
        return remoteDataSource.signUp(userData)
            .switchMap {
                login(user.email, user.password)
            }
    }

    override fun saveProfile(profile: ProfileEntity): Observable<Long> {

        return localDataSource.getActiveUser()
            .flatMap { userData ->
                callRemote(profile, userData)
            }
    }

    override fun getProfile(userId: Long): Observable<ProfileEntity> {
        val localData = localDataSource.getProfile(userId)
            .map { profile ->
                profileMapper.from(profile)
            }
        return remoteDataSource.getProfile(userId)
            .map { profile ->
                localDataSource.saveProfile(userId, profile)
                profileMapper.from(profile)
            }
            .concatWith(localData)
    }

    override fun savePlan(plan: PlanEntity): Observable<Long> {
        return localDataSource.getActiveUser()
            .flatMap { userData ->
                savePlanInRemote(plan, userData)
            }
    }

    override fun getDiet(params: GetDietTask.Params): Observable<DietEntity> {
        return remoteDataSource.getDiet(params.planId, params.userId)
            .map { diet ->
                dietMapper.from(diet)
            }
    }

    override fun getProgress(userId: Long): Observable<List<ProgressEntity>> {
        return remoteDataSource.getProgress(userId)
            .map {
                it.map { progress ->
                    progressMapper.from(progress)
                }
            }
    }

    override fun saveProgress(progressEntity: ProgressEntity): Observable<Long> {
        val progressData = progressMapper.to(progressEntity)
        return remoteDataSource.saveProgress(progressData)
    }

    private fun savePlanInRemote(plan: PlanEntity, userData: UserTokenData): Observable<Long> {
        val t = planDomainDataMapper.to(plan, userData.id)
        return remoteDataSource.savePlan(t).map {
            it
        }
    }

    private fun callRemote(profile: ProfileEntity, userData: UserTokenData): Observable<Long> {
        val profileData = profileMapper.to(profile)
        val userId = userData.id
        profileData.userId = userId

        return remoteDataSource.saveProfile(profileData)
            .map {
                val profileWithId = ProfileData(
                    id = it,
                    isVegetarian = profile.isVegetarian,
                    isPregnant = profile.isPregnant,
                    gender = profile.gender,
                    weight = profile.weight,
                    height = profile.height,
                    age = profile.age,
                    userId = userId
                )
                localDataSource.saveProfile(userId, profileWithId).subscribe()
                it
            }
    }

}