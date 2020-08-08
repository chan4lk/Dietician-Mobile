package com.dietician.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token")
data class TokenLocal(
    @PrimaryKey @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "token") val token: String
)