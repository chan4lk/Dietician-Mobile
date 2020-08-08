package com.dietician.local.source

import com.dietician.data.model.PlanData
import com.dietician.data.model.TokenData
import com.dietician.data.repository.LocalDataSource
import com.dietician.local.database.PlanDAO
import com.dietician.local.database.TokenDAO
import com.dietician.local.mapper.PlanDataLocalMapper
import com.dietician.local.mapper.TokenDataLocalMapper
import io.reactivex.Observable
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val tokenMapper: TokenDataLocalMapper,
    private val planMapper: PlanDataLocalMapper,
    private val tokenDAO: TokenDAO,
    private val planDAO: PlanDAO
) : LocalDataSource {
    override fun login(userName: String, password: String): Observable<TokenData> {
        return tokenDAO.getToken(userName)
            .map {
                tokenMapper.from(it)
            }
    }

    override fun getToken(): Observable<TokenData> {
        return tokenDAO.getActiveToken()
            .map {
                tokenMapper.from(it)
            }
    }

    override fun getPlans(userName: String): Observable<List<PlanData>> {
        return planDAO.getUserPlans(userName)
            .map { plans ->
                plans.map { planMapper.from(it) }
            }
    }

    override fun saveToken(userName: String, tokenData: TokenData) {
        return tokenDAO.addToken(
            tokenMapper.to(tokenData, userName)
        )
    }

    override fun savePlans(userName: String, plans: List<PlanData>) {
        return planDAO.addPlans(
            plans = plans.map { planMapper.to(it, userName) }
        )
    }
}