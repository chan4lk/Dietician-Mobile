package com.dietician.remote.api

import com.dietician.remote.model.PlanWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanApi {
    @GET("Plans/User/{email}")
    fun getPlans(@Path("email") email: String): Observable<List<PlanWrapper>>
}


