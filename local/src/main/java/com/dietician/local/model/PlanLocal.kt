package com.dietician.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plans")
data class PlanLocal(
    @PrimaryKey @ColumnInfo(name = "plan_id") val id: Number,
    @ColumnInfo(name = "user_id") val userId: Number,
    @ColumnInfo(name = "target") val target: Number,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "duration") val duration: Number,
    @ColumnInfo(name = "startDate") val startDate: String,
    @ColumnInfo(name = "status") val status: Number
)