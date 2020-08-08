package com.dietician.local.mapper

interface Mapper<T, E> {

    fun from(e: E): T

    fun to(t: T, userName: String): E

}