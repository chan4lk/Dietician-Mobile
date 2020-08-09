package com.dietician.data.model

data class ProfileData(
    val id: Long,
    val name: String,
    val age: Int,
    val height: Double,
    val weight: Double,
    val gender: Int,
    val isPregnant: Boolean,
    val isVegetarian: Boolean,
    var userId: Long
)