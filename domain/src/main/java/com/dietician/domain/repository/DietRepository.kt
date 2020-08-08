package com.dietician.domain.repository

import com.dietician.domain.entities.PlanEntity
import com.dietician.domain.entities.TokenEntity
import io.reactivex.Observable

interface DietRepository {

    fun login(userName: String, password: String): Observable<TokenEntity>

    fun getPlans(token: String): Observable<List<PlanEntity>>

}