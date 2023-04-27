package com.example.myapplication.api

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "ResultX")
data class ResultX(
    @PrimaryKey val id: String,
    @TypeConverters(StringListTypeConverter::class)
    val category: List<String>,
    val content: String,
    @TypeConverters(StringListTypeConverter::class)
    val country: List<String>,
    @TypeConverters(StringListTypeConverter::class)
    val creator: List<String>,
    val description: String,
    val full_description: String,
    val image_url: String,
    @TypeConverters(StringListTypeConverter::class)
    val keywords: List<String>,
    val language: String,
    val link: String,
    val pubDate: String,
    val source_id: String,
    val title: String,
    @TypeConverters(AnyTypeConverter::class)
    val video_url: Any
)