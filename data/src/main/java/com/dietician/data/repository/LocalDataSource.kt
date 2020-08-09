package com.dietician.data.repository

import com.dietician.data.model.PlanData
import com.dietician.data.model.ProfileData
import com.dietician.data.model.TokenData
import com.dietician.data.model.UserData
import io.reactivex.Observable

interface LocalDataSource {
    fun login(userName: String, password: String): Observable<TokenData>
    fun saveUser(user: UserData)
    fun getToken(): Observable<TokenData>
    fun getActiveUser(): Observable<UserData>
    fun getPlans(userName: String): Observable<List<PlanData>>
    fun saveToken(userName: String, tokenData: TokenData)
    fun savePlans(userName: String, plans: List<PlanData>)
    fun saveProfile(userName: String, profile: ProfileData)
}