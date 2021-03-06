package com.dietician.data.model

data class PlanData(
    val id: Number,
    val name: String,
    val activityLevel: Int,
    val goal: Int,
    val pace: Int,
    val target: Number,
    val duration: Number,
    val startDate: String,
    val status: Number,
    val userId: Long
)