package com.dietician.presentation.model

data class Food (
    val id: Long,
    val name: String,
    val fat: Double,
    val protine: Double,
    val carbohydrate: Double,
    val isVeg: Boolean,
    val type: Int,
    val foodCategory: Int,
    val foodQuantity: Double

)