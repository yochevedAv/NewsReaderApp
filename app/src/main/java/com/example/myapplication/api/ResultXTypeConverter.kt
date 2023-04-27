package com.example.myapplication.api

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ResultXTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromJson(value: String): List<ResultX> {
        val listType = object : TypeToken<List<ResultX>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toJson(list: List<ResultX>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromString(value: String): ResultX {
        return gson.fromJson(value, ResultX::class.java)
    }

    @TypeConverter
    fun toString(user: ResultX): String {
        return gson.toJson(user)
    }
}
