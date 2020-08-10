package com.dietician.local.database

import androidx.room.*
import com.dietician.local.model.PlanLocal
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface PlanDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPlans(plans: List<PlanLocal>)

    @Query("SELECT * FROM plans WHERE user_id = :userId")
    fun getUserPlans(userId: Long): Observable<List<PlanLocal>>

    @Query("SELECT * FROM plans")
    fun getActiveUserPlans(): Observable<List<PlanLocal>>

    @Update
    fun updatePlan(plan: PlanLocal): Completable

    @Query("DELETE FROM plans")
    fun clearCachedPlans(): Completable
}