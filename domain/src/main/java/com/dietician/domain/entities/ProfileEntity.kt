package com.dietician.domain.entities

data class ProfileEntity(
    val id: Long,
    val name: String,
    val age: Int,
    val height: Double,
    val weight: Double,
    val gender: Int,
    val isPregnant: Boolean,
    val isVegetarian: Boolean,
    val userId: Long
)