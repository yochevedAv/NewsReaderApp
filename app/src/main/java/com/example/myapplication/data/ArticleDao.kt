package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ArticleDao {
    @Insert
    fun insert(articles: List<Article>)

    @Query("SELECT * FROM articles")
    fun getArticles(): List<Article>

    @Query("SELECT COUNT(*) FROM articles")
    fun getNumberOfItems(): Int
}

