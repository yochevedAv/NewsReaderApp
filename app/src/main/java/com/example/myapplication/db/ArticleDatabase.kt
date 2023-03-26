package com.example.myapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.api.ArticleJson
import com.example.myapplication.api.MyTypeConverters

@TypeConverters(value = [MyTypeConverters::class])
@Database(
    entities= [ArticleJson::class],
    version =1,
    exportSchema = false
)

abstract class ArticleDatabase : RoomDatabase() {
    abstract fun localArticleDAO() : ILocalArticleDAO

    companion object {
        @Volatile
        private var INSTANCE : ArticleDatabase? = null

        fun getDatabase(context : Context) : ArticleDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    "room_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}