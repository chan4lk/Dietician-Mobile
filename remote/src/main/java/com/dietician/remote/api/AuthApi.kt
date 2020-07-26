package com.dietician.remote.api

import com.dietician.data.model.Token
import com.dietician.remote.model.Credential
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("Auths")
    fun login(@Body credential: Credential): Observable<Token>

}