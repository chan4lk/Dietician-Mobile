package com.dietician.data.repository

import com.dietician.data.model.*
import io.reactivex.Observable

interface RemoteDataSource {
    fun login(userName: String, password: String): Observable<UserTokenData>

    fun getPlans(userId: Long): Observable<List<PlanData>>

    fun signUp(user: UserData): Observable<Long>

    fun saveProfile(profile: ProfileData): Observable<Long>

    fun getProfile(userId: Long): Observable<ProfileData>

    fun savePlan(planData: PlanData): Observable<Long>

    fun getDiet(planId: Long, userId: Long): Observable<DietData>
}