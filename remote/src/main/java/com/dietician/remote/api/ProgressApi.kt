package com.dietician.remote.api

import com.dietician.remote.model.PaginatedResponse
import com.dietician.remote.model.Progress
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProgressApi {

    @GET("api/Progress/User/{id}")
    fun getProgress(@Path("id") userId: Long): Observable<PaginatedResponse<Progress>>

    @POST("api/Progress")
    fun saveProgress(@Body progress: Progress): Observable<Long>
}