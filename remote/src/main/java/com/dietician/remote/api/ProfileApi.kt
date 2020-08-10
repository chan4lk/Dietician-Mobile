package com.dietician.remote.api

import com.dietician.remote.model.Profile
import io.reactivex.Observable
import retrofit2.http.*

interface ProfileApi {
    @POST("Profiles")
    fun saveProfile(@Body profile: Profile): Observable<Long>

    @PUT("Profiles/{id}")
    fun updateProfile(@Path("id") id: Long, @Body profile: Profile): Observable<Long>

    @GET("Profiles/User/{id}")
    fun getProfile(@Path("id") userId: Long): Observable<Profile>
}