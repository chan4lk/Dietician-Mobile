package com.dietician.domain.entities

data class FoodItemEntity(
    val id: Long,
    val name: String,
    val fat: Double,
    val protine: Long,
    val carbohydrate: Long,
    val isVeg: Boolean,
    val type: Int,
    val foodCategory: Int,
    val foodQuantity: Long
)