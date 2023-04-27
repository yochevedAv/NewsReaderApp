package com.example.myapplication.api

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
@Entity(tableName = "Articles")
data class ArticleJson(
    @PrimaryKey val nextPage: String,
    val results: List<ResultX>,
    val status: String,
    val totalResults: Int
)