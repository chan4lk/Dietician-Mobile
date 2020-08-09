package com.dietician.data.repository

import com.dietician.data.mapper.PlanDomainDataMapper
import com.dietician.data.mapper.TokenDomainDataMapper
import com.dietician.data.mapper.UserDomainDataMapper
import com.dietician.domain.entities.PlanEntity
import com.dietician.domain.entities.TokenEntity
import com.dietician.domain.entities.UserEntity
import com.dietician.domain.repository.DietRepository
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class DietRepositoryImpl @Inject constructor(
    private val tokenDomainDataMapper: TokenDomainDataMapper,
    private val planDomainDataMapper: PlanDomainDataMapper,
    private val userDomainDataMapper: UserDomainDataMapper,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : DietRepository {

    override fun login(userName: String, password: String): Observable<TokenEntity> {
        val tokenObservable = localDataSource.login(userName, password)
            .map { tokenDomainDataMapper.from(it) }

        return remoteDataSource.login(userName, password)
            .map {
                localDataSource.saveToken(userName, it)
                tokenDomainDataMapper.from(it)
            }.onErrorResumeNext(Observable.empty())
            .concatWith(tokenObservable)
    }

    override fun getPlans(token: String): Observable<List<PlanEntity>> {
        val localPlans = localDataSource.getPlans(token)
            .map { plans ->
                plans.map { planDomainDataMapper.from(it) }
            }

        return remoteDataSource.getPlans(token)
            .map { plans ->
                localDataSource.savePlans(token, plans)
                plans.map { planDomainDataMapper.from(it) }
            }.onErrorResumeNext(Observable.empty())
            .concatWith(localPlans)
    }

    override fun signUp(user: UserEntity): Observable<Long> {
        val userData = userDomainDataMapper.to(user)
        return remoteDataSource.signUp(userData)
            .map {
                localDataSource.saveUser(userData)
                it
            }
            .onErrorResumeNext(Function { Observable.just(0) })
    }

}