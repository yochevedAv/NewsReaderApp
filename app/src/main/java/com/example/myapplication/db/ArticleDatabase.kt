package com.example.myapplication.db

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.api.AnyTypeConverter
import com.example.myapplication.api.ArticleJson
import com.example.myapplication.api.ResultX
import com.example.myapplication.api.ResultXTypeConverter
import com.example.myapplication.api.StringListTypeConverter
import com.example.myapplication.data.Article
import com.example.myapplication.data.ArticleDao
import okhttp3.RequestBody

@Database(entities = [Article::class], version = 2)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        fun getInstance(context: Context): ArticleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    "my_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }


    }
}