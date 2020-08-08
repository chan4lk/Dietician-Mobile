package com.dietician.remote.api

import com.dietician.remote.model.PlanWrapper
import io.reactivex.Observable
import retrofit2.http.GET

interface PlanApi {
    @GET("Plans")
    fun getPlans(): Observable<List<PlanWrapper>>
}


