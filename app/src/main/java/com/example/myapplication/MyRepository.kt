package com.example.myapplication
import android.app.Application
import android.content.Context
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.myapplication.api.AnyTypeConverter
import com.example.myapplication.api.ArticleJson
import com.example.myapplication.api.ResultX
import com.example.myapplication.api.StringListTypeConverter
import com.example.myapplication.data.Resource
import com.example.myapplication.db.ArticleDao
import com.example.myapplication.db.ArticleDatabase
import com.example.myapplication.network.Api
import com.example.myapplication.network.ApiConfigurations
import retrofit2.awaitResponse


class MyRepository (private val context: Context) {

    private val apiService = ApiConfigurations.getClient<Api>()
    private val articleDao: ArticleDao = ArticleDatabase.getDatabase(context).articleDao()

    suspend fun getArticles(): Resource<ArticleJson> {
        return try {
            val response = apiService.getNews()
            if (response.isExecuted) {
                val data = response.execute().body()
                if (data != null) {
                    // Save data to local database
                    articleDao.insertArticleObject(data)
                    Resource.Success(data)
                } else {
                    Resource.Error("Response body is null")
                }
            } else {
                Resource.Error("Response code: ${response.request().body}")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}
