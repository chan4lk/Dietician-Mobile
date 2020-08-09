package com.dietician.local.database

import androidx.room.*
import com.dietician.local.model.ProfileLocal
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface ProfileDAO {
    @Query("SELECT * FROM profile WHERE profile_id = :id")
    fun getProfile(id: Long): Observable<ProfileLocal>

    @Query("SELECT * FROM profile LIMIT 1")
    fun getActiveProfile(): Observable<ProfileLocal>

    @Update
    fun updateProfile(profile: ProfileLocal): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addProfile(profile: ProfileLocal): Completable

    @Query("DELETE FROM profile")
    fun clearCachedProfiles(): Completable
}