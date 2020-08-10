package com.dietician.remote.model

data class PaginatedResponse<T>(
    val count: Int,
    val list: List<T>
)