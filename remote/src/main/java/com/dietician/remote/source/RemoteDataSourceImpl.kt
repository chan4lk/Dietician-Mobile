package com.dietician.remote.source

import com.dietician.data.model.PlanData
import com.dietician.data.model.TokenData
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
    override fun login(userName: String, password: String): Observable<TokenData> {
        val credential = Credential(userName, password)
        return authApi.login(credential).map { response ->
            TokenData(response.token)
        }
    }

    override fun getPlans(token: String): Observable<List<PlanData>> {
        return planApi.getPlans().map { response ->
            response.map { plan -> mapper.mapToPlan(plan) }
        }
    }

}