package com.dietician.remote.api

import com.dietician.remote.model.PlanWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header

interface PlanApi {
    @GET("Plans")
    fun getPlans(@Header("Authorization") token: String): Observable<List<PlanWrapper>>
}


