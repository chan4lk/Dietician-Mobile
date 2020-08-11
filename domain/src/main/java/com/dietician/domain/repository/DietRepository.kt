package com.dietician.domain.repository

import com.dietician.domain.entities.*
import com.dietician.domain.usecases.GetDietTask
import io.reactivex.Observable

interface DietRepository {

    fun login(userName: String, password: String): Observable<UserTokenEntity>

    fun getPlans(userId: Long): Observable<List<PlanEntity>>

    fun signUp(user: UserEntity): Observable<UserTokenEntity>

    fun saveProfile(profile: ProfileEntity): Observable<Long>

    fun getProfile(userId: Long): Observable<ProfileEntity>

    fun savePlan(plan: PlanEntity): Observable<Long>

    fun getDiet(params: GetDietTask.Params): Observable<DietEntity>

    fun getProgress(userId: Long): Observable<List<ProgressEntity>>
}