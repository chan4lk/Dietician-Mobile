package com.dietician.data.repository

import com.dietician.data.model.PlanData
import com.dietician.data.model.ProfileData
import com.dietician.data.model.UserTokenData
import io.reactivex.Completable
import io.reactivex.Observable

interface LocalDataSource {
    fun login(userName: String, password: String): Observable<UserTokenData>
    fun saveUser(user: UserTokenData): Completable
    fun getActiveUser(): Observable<UserTokenData>
    fun getPlans(userId: Long): Observable<List<PlanData>>
    fun savePlans(userId: Long, plans: List<PlanData>)
    fun saveProfile(userId: Long, profile: ProfileData): Completable
}