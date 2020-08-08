package com.dietician.data.repository

import com.dietician.data.model.PlanData
import com.dietician.data.model.TokenData
import io.reactivex.Observable

interface LocalDataSource {
    fun login(userName: String, password: String): Observable<TokenData>

    fun getPlans(userName: String): Observable<List<PlanData>>
    fun saveToken(userName: String, tokenData: TokenData)
    fun savePlans(userName: String, plans: List<PlanData>)
}