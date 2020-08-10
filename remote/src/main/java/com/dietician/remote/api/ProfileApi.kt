package com.dietician.remote.api

import com.dietician.remote.model.Profile
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProfileApi {
    @POST("Profiles")
    fun saveProfile(@Body profile: Profile): Observable<Long>

    @GET("Profiles/User/{id}")
    fun getProfile(@Path("id") userId: Long): Observable<Profile>
}