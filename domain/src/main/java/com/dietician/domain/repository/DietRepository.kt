package com.dietician.domain.repository

import com.dietician.domain.entities.PlanEntity
import com.dietician.domain.entities.ProfileEntity
import com.dietician.domain.entities.UserEntity
import com.dietician.domain.entities.UserTokenEntity
import io.reactivex.Observable

interface DietRepository {

    fun login(userName: String, password: String): Observable<UserTokenEntity>

    fun getPlans(userName: String): Observable<List<PlanEntity>>

    fun signUp(user: UserEntity): Observable<UserTokenEntity>

    fun saveProfile(profile: ProfileEntity): Observable<Long>
}