package com.dietician.remote.model

data class PlanWrapper(
    val id: Number,
    val target: Number,
    val name: String,
    val duration: Number,
    val startDate: String,
    val status: Number
)
