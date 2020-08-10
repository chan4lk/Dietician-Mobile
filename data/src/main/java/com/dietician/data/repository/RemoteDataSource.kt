package com.dietician.data.repository

import com.dietician.data.model.PlanData
import com.dietician.data.model.ProfileData
import com.dietician.data.model.UserData
import com.dietician.data.model.UserTokenData
import io.reactivex.Observable

interface RemoteDataSource {
    fun login(userName: String, password: String): Observable<UserTokenData>

    fun getPlans(userId: Long): Observable<List<PlanData>>

    fun signUp(user: UserData): Observable<Long>

    fun saveProfile(profile: ProfileData): Observable<Long>

    fun getProfile(userId: Long): Observable<ProfileData>
}