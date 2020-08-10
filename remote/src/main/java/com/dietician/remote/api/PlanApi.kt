package com.dietician.remote.api

import com.dietician.remote.model.PlanWrapper
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PlanApi {
    @GET("Plans/User/{id}")
    fun getPlans(@Path("id") id: Long): Observable<List<PlanWrapper>>

    @POST("Plans")
    fun savePlan(@Body plan: PlanWrapper): Observable<Long>
}


