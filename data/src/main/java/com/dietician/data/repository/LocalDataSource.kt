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
    fun getPlans(userName: String): Observable<List<PlanData>>
    fun savePlans(userName: String, plans: List<PlanData>)
    fun saveProfile(userName: String, profile: ProfileData): Completable
}