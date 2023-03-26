package com.example.myapplication.api

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Articles")
data class ArticleJson(

    val nextPage: String,
    val ResultItems: ResultXList,
    val status: String,
    val totalResults: Int,
    @PrimaryKey val id: Int
)