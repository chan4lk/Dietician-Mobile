package com.dietician.domain.entities

open class PlanEntity(
    val id: Number,
    val target: Number,
    val name: String,
    val duration: Number,
    val startDate: String,
    val status: Number
)