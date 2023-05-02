package com.example.myapplication.ui.news

import com.example.myapplication.api.ArticleJson
import com.example.myapplication.network.Api
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsService @Inject constructor(retrofit: Retrofit) {
    private val api: Api = retrofit.create(Api::class.java)
    
    suspend fun getNews(): Response<ArticleJson> {
        return api.getNews()
    }
}