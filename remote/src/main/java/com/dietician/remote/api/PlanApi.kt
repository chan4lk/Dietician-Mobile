package com.dietician.remote.api

import com.dietician.remote.model.PaginatedResponse
import com.dietician.remote.model.Plan
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PlanApi {
    @GET("Plans/User/{id}")
    fun getPlans(@Path("id") id: Long): Observable<PaginatedResponse<Plan>>

    @POST("Plans")
    fun savePlan(@Body plan: Plan): Observable<Long>
}


