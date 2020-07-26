package com.dietician.remote.source

import com.dietician.data.model.Plan
import com.dietician.data.model.Token
import com.dietician.data.repository.RemoteDataSource
import com.dietician.remote.api.AuthApi
import com.dietician.remote.api.PlanApi
import com.dietician.remote.mapper.ResponseMapper
import com.dietician.remote.model.Credential
import io.reactivex.Observable
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val authApi: AuthApi,
    private val planApi: PlanApi,
    private val mapper: ResponseMapper
) : RemoteDataSource {
    override fun login(userName: String, password: String): Observable<Token> {
        val credential = Credential(userName, password)
        return authApi.login(credential).map { response ->
            Token(response.token)
        }
    }

    override fun getPlans(token: String): Observable<List<Plan>> {
        return planApi.getPlans(token).map { response ->
            response.map { plan -> mapper.mapToPlan(plan) }
        }
    }

}