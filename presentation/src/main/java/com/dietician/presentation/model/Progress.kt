package com.dietician.presentation.model

data class Progress(
    val id: Long,
    val userId: Long,
    val date: String,
    val weight: Double
)