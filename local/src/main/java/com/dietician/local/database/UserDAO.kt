package com.dietician.local.database

import androidx.room.*
import com.dietician.local.model.UserLocal
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface UserDAO {
    @Query("SELECT * FROM user WHERE user_name = :userName")
    fun getUser(userName: String): Observable<UserLocal>

    @Query("SELECT * FROM user LIMIT 1")
    fun getActiveUser(): Observable<UserLocal>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateUser(user: UserLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: UserLocal): Completable

    @Query("DELETE FROM user")
    fun clearCachedUsers(): Completable
}