package com.dietician.local.database

import androidx.room.*
import com.dietician.local.model.TokenLocal
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface TokenDAO {
    @Query("SELECT * FROM token WHERE user_name = :userName")
    fun getToken(userName: String): Observable<TokenLocal>

    @Query("SELECT * FROM token LIMIT 1")
    fun getActiveToken(): Observable<TokenLocal>

    @Update
    fun updateToken(tokenLocal: TokenLocal): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addToken(tokenLocal: TokenLocal): Completable

    @Query("DELETE FROM token")
    fun clearCachedToken(): Completable
}