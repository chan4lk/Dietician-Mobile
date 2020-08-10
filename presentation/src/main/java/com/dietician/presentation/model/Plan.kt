package com.dietician.presentation.model

data class Plan(
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