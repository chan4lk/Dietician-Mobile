package com.dietician.data.repository

import com.dietician.data.model.PlanData
import com.dietician.data.model.ProfileData
import com.dietician.data.model.UserData
import com.dietician.data.model.UserTokenData
import io.reactivex.Observable

interface RemoteDataSource {
    fun login(userName: String, password: String): Observable<UserTokenData>

    fun getPlans(userName: String): Observable<List<PlanData>>

    fun signUp(user: UserData): Observable<Long>

    fun saveProfile(profile: ProfileData): Observable<Long>
}