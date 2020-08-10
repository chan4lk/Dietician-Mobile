package com.dietician.remote.model

data class Diet(
    val userId: Long,
    val planId: Long,
    val date: String,
    val message: String,
    val extraCalorieAmount: Double,
    val isError: Boolean,
    val foodItems: List<Food>
)