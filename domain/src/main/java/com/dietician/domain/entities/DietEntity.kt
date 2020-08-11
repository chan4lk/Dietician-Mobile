package com.dietician.domain.entities

data class DietEntity(
    val userId: Long,
    val planId: Long,
    val date: String,
    val message: String,
    val extraCalorieAmount: Double,
    val isError: Boolean,
    val foodItems: List<FoodItemEntity>
)