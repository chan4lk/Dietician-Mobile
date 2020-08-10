package com.dietician.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileLocal(
    @PrimaryKey @ColumnInfo(name = "profile_id") val id: Long,
    @ColumnInfo(name = "user_id") val userId: Long,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "height") val height: Double,
    @ColumnInfo(name = "weight") val weight: Double,
    @ColumnInfo(name = "gender") val gender: Int,
    @ColumnInfo(name = "is_pregnant") val isPregnant: Boolean,
    @ColumnInfo(name = "is_vegetarian") val isVegetarian: Boolean
)