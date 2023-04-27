package com.example.myapplication.api

import androidx.room.TypeConverter
import com.google.gson.Gson

class AnyTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromJsonString(value: String?): Any? {
        if (value == null) {
            return null
        }
        return try {
            gson.fromJson(value, Any::class.java)
        } catch (e: Exception) {
            null
        }
    }

    @TypeConverter
    fun toJsonString(value: Any?): String? {
        if (value == null) {
            return null
        }
        return try {
            gson.toJson(value)
        } catch (e: Exception) {
            null
        }
    }
}
