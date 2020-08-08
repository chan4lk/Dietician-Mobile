package com.dietician.presentation.model

data class Plan(
    val id: Number,
    val target: Number,
    val name: String,
    val duration: Number,
    val startDate: String,
    val status: Number
)