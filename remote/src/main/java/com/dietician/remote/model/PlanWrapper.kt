package com.dietician.remote.model

data class PlanWrapper(
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
