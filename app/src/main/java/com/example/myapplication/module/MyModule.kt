package com.example.myapplication.module

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.ArticleDao
import com.example.myapplication.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MyModule {
    @Provides
    @Singleton
    fun provideMyDao(database: ArticleDatabase): ArticleDao {
        return database.articleDao()
    }

    @Provides
    @Singleton
    fun provideMyDatabase(@ApplicationContext context: Context): ArticleDatabase {
        return Room.databaseBuilder(
            context,
            ArticleDatabase::class.java,
            "my_database"
        ).fallbackToDestructiveMigration().build()
    }

}
