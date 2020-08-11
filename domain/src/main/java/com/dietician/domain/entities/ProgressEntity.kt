package com.dietician.domain.entities

data class ProgressEntity(
    val id: Long,
    val userId: Long,
    val date: String,
    val weight: Double
)