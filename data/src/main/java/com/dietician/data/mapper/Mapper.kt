package com.dietician.data.mapper

interface Mapper<T, E> {

    fun from(e: E): T

    fun to(t: T): E

    fun from(e: E, userId: Long): T

    fun to(t: T, userId: Long): E

}