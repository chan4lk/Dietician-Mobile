package com.dietician.domain.entities

import java.util.*

data class DietEntity(
    val userId: Long,
    val planId: Long,
    val date: Date,
    val message: String,
    val extraCalorieAmount: Double,
    val isError: Boolean,
    val foodItems: List<FoodItemEntity>
)