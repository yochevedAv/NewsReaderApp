package com.example.myapplication
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.myapplication.api.AnyTypeConverter
import com.example.myapplication.api.ArticleJson
import com.example.myapplication.api.ResultX
import com.example.myapplication.api.StringListTypeConverter
import com.example.myapplication.data.Resource
import com.example.myapplication.db.ArticleDao
import retrofit2.awaitResponse


class MyRepository {

    private val api = ApiConfigurations.

    suspend fun getArticles(): Resource<List<ArticleJson>> {
        return try {
            val response = apiRequests.getNews()
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
