package com.example.myapplication.api

import androidx.room.TypeConverter
import com.google.gson.Gson

class MyTypeConverters {

    @TypeConverter
    fun fromResultXToJSON(resultXlist: ResultXList): String {
        return Gson().toJson(resultXlist)
    }
    @TypeConverter
    fun fromJSONToResultX(json: String): ResultXList {
        return Gson().fromJson(json, ResultXList::class.java)
    }
}