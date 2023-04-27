package com.example.myapplication.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.api.ResultX


data class ArticleEntity(
    @PrimaryKey val nextPage: String,
    val results: List<ResultX>,
    val status: String,
    val totalResults: Int
)