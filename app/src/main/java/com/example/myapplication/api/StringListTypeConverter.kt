package com.example.myapplication.api

import androidx.room.TypeConverter

class StringListTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",")
    }

    @TypeConverter
    fun toString(list: List<String>): String {
        return list.joinToString(separator = ",")
    }
}