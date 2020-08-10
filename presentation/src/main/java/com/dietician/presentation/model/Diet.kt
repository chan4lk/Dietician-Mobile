package com.dietician.presentation.model

import java.util.*

data class Diet(
    val userId: Long,
    val planId: Long,
    val date: Date,
    val message: String,
    val extraCalorieAmount: Double,
    val isError: Boolean,
    val foodItems: List<Food>
)