package com.dietician.remote.api

import com.dietician.data.model.TokenData
import com.dietician.data.model.UserData
import com.dietician.remote.model.Credential
import com.dietician.remote.model.User
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {
    @POST("Auths")
    fun login(@Body credential: Credential): Observable<TokenData>

    @POST("Users")
    fun signUp(@Body user: User): Observable<Long>

    @GET("Users/ByEmail/{email}")
    fun getUserDetails(@Path("email") email: String): Observable<UserData>
}