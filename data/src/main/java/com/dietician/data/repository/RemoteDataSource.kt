package com.dietician.data.repository

import com.dietician.data.model.PlanData
import com.dietician.data.model.ProfileData
import com.dietician.data.model.TokenData
import com.dietician.data.model.UserData
import io.reactivex.Observable

interface RemoteDataSource {
    fun login(userName: String, password: String): Observable<TokenData>

    fun getPlans(token: String): Observable<List<PlanData>>

    fun signUp(user: UserData): Observable<Long>

    fun saveProfile(profile: ProfileData): Observable<Long>
}