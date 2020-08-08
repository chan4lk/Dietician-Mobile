package com.dietician.local.mapper

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromNumber(value: Number?): Long? {
        return value?.toLong()
    }

    @TypeConverter
    fun toNumber(value: Long?): Number? {
        return value
    }
}