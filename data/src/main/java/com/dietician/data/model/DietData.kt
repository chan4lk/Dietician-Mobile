package com.dietician.data.model

data class DietData(
    val userId: Long,
    val planId: Long,
    val date: String,
    val message: String,
    val extraCalorieAmount: Double,
    val isError: Boolean,
    val foodItems: List<FoodData>
)