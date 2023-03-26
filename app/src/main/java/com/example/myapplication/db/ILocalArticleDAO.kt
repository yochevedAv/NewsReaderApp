package com.example.myapplication.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.api.ArticleJson

@Dao
interface ILocalArticleDAO {

    @Query("select * from Articles")
    fun getAllArticles(): LiveData<List<ArticleJson>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(article: ArrayList<ArticleJson>)

    @Delete
    fun delete(article: ArticleJson)
}