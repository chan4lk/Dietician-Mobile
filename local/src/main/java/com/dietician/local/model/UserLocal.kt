package com.dietician.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserLocal(
    @PrimaryKey @ColumnInfo(name = "user_id") val id: Long,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "token") val token: String
)