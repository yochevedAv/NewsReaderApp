package com.example.myapplication.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.api.AnyTypeConverter
import com.example.myapplication.api.ArticleJson
import com.example.myapplication.api.ResultX
import com.example.myapplication.api.ResultXTypeConverter
import com.example.myapplication.api.StringListTypeConverter
import okhttp3.RequestBody

@Database(entities = [ArticleJson::class],version = 1)
@TypeConverters(ResultXTypeConverter::class,
                StringListTypeConverter::class,
                AnyTypeConverter::class)

abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        fun getInstance(): Any {

        }
    }
}

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticleObject(articles: List<ArticleJson>)

    @Query("SELECT * FROM Articles")
    suspend fun getArticleObject(): List<ArticleJson>
}
