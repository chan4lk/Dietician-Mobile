package com.dietician.data.repository

import com.dietician.data.model.Plan
import com.dietician.data.model.Token
import io.reactivex.Observable

interface RemoteDataSource {
    fun login(userName: String, password: String): Observable<Token>

    fun getPlans(token: String): Observable<List<Plan>>
}