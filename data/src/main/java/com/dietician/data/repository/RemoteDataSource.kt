package com.dietician.data.repository

import com.dietician.data.model.PlanData
import com.dietician.data.model.TokenData
import io.reactivex.Observable

interface RemoteDataSource {
    fun login(userName: String, password: String): Observable<TokenData>

    fun getPlans(token: String): Observable<List<PlanData>>
}