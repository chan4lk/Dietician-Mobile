package com.dietician.remote.api

import com.dietician.remote.model.Diet
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface DietApi {

    @GET("Diet")
    fun getDiet(
        @Query("UserId") userId: Long,
        @Query("PlanId") planId: Long,
        @Query("Date") date: String,
        @Query("Type") type: Int
    ): Observable<Diet>
}