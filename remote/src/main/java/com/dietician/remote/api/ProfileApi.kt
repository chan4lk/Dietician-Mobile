package com.dietician.remote.api

import com.dietician.remote.model.Profile
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface ProfileApi {
    @POST("Profile")
    fun saveProfile(@Body profile: Profile): Observable<Long>
}