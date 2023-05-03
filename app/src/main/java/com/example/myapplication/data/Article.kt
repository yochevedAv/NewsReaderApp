package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import retrofit2.http.Url

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val imageUrl: String
)
