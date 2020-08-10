package com.dietician.data.model

import java.util.*

data class DietData(
    val userId: Long,
    val planId: Long,
    val date: Date,
    val message: String,
    val extraCalorieAmount: Double,
    val isError: Boolean,
    val foodItems: List<FoodData>
)