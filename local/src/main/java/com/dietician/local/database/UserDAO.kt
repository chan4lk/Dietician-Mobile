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

    @Update
    fun updateUser(user: UserLocal): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: UserLocal)

    @Query("DELETE FROM user")
    fun clearCachedUsers(): Completable
}